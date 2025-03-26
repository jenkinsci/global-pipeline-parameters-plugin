package io.jenkins.plugins.gparams;

import hudson.Extension;
import hudson.model.TaskListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;
import org.springframework.lang.NonNull;

/**
 * Write global parameter that is can be read any Jobs in JENKINS with by key 'name'
 */
public class GParamsWriteStep extends Step {

    private final String name;
    private final String value;

    @DataBoundConstructor
    public GParamsWriteStep(String name, String value) {
        if (!StringUtils.isAlphanumeric(name) || name.length() > Parameters.NameLen) {
            throw new IllegalArgumentException("The '" + name + "' is not a valid gparams variable name");
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(this, context);
    }

    @Extension
    public static final class DescriptorImpl extends StepDescriptor {

        @Override
        public String getFunctionName() {
            return "gpWrite";
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "Write global parameter";
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(TaskListener.class);
        }
    }

    private static class Execution extends SynchronousStepExecution<Void> {

        private final transient GParamsWriteStep step;

        public Execution(GParamsWriteStep step, StepContext context) {
            super(context);
            this.step = step;
        }

        @Override
        protected Void run() throws Exception {
            String name = step.getName();
            String value = step.getValue();

            if (!StringUtils.isAlphanumeric(name) || name.length() > Parameters.NameLen) {
                throw new IllegalArgumentException("The '" + name + "' is not a valid gparams variable name");
            }

            PrintStream logger =
                    Objects.requireNonNull(getContext().get(TaskListener.class)).getLogger();

            // Write value in file with name 'name'. Create directory, if it is not
            // exist.
            String filePath = Parameters.GParamDirectoryName + name;

            File file = new File(filePath);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Create directory " + parent.getAbsolutePath() + " error.");
            }

            if (!file.exists() && !file.createNewFile()) {
                throw new IOException("Create file " + file.getAbsolutePath() + " error");
            }

            logger.println("Create file " + file.getAbsolutePath());

            try (FileOutputStream out = new FileOutputStream(file);
                    @SuppressWarnings("unused")
                            FileLock lock = out.getChannel().lock()) {
                out.write(value.getBytes(StandardCharsets.UTF_8));
            }

            return null;
        }

        private static final long serialVersionUID = 1L;
    }
}
