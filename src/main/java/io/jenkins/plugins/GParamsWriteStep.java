package io.jenkins.plugins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.imageio.IIOException;

/**
 * Write value in global map with key 'name'
 */
public class GParamsWriteStep extends Builder implements SimpleBuildStep {

    private final String name;
    private final String value;
    static final String GParamDirectoryName = "gparams/";

    @DataBoundConstructor
    public GParamsWriteStep(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void perform(
            @NonNull Run<?, ?> run,
            @NonNull FilePath workspace,
            @NonNull EnvVars env,
            @NonNull Launcher launcher,
            @NonNull TaskListener listener)
            throws InterruptedException, IOException {

        // Each value is stored in file with name 'name' in directory GParamsDirectoryName.
        // Create this directory, if it is not exist.
        String filePath = GParamDirectoryName + name;
        File file = new File(filePath);
        File parent = file.getParentFile();

        if(parent != null && !parent.exists() && !parent.mkdirs()) {
            listener.error("Can't create directory " + parent.getAbsolutePath());
            throw new IOException("GParams directory create error.");
        }

        boolean fileCreatedSuccessfully = file.createNewFile();
        if(!fileCreatedSuccessfully) {
            listener.error("GParams create new file error. Path: " + file.getAbsolutePath());
            throw new IIOException("GParams create new file error. Path: " + file.getAbsolutePath());
        }

        // Write value to file
        try(PrintWriter writer = new PrintWriter(file)) {
            writer.write(value);
        }
    }

    @Symbol("gpwrite")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }
    }
}
