package io.jenkins.plugins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.TaskListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Set;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Read global value that common for all JENKINS Jobs
 */
public class GParamsReadStep extends Step {

    private final String name;

    @DataBoundConstructor
    public GParamsReadStep(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(this, context);
    }

    @Extension
    public static final class DescriptorImpl extends StepDescriptor {

        @Override
        public String getFunctionName() {
            return "gpread";
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "Read global parameter";
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return Collections.singleton(TaskListener.class);
        }
    }

    private static class Execution extends SynchronousStepExecution<String> {

        private final transient GParamsReadStep step;

        public Execution(GParamsReadStep step, StepContext context) {
            super(context);
            this.step = step;
        }

        @Override
        @SuppressWarnings("unused")
        protected String run() throws Exception {
            // Read value from file with name 'name' and return its content.
            String name = step.getName();
            String filePath = Parameters.GParamDirectoryName + name;

            try {
                File file = new File(filePath);
                try (FileInputStream fileInputStream = new FileInputStream(file);
                        FileLock lock = fileInputStream.getChannel().lock()) {
                    return new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
                }
            } catch (IOException e) {
                throw new IOException("gparam parameter " + name + " not exist");
            }
        }

        private static final long serialVersionUID = 1L;
    }
}
