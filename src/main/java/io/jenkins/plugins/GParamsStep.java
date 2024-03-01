package io.jenkins.plugins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class GParamsStep extends Builder implements SimpleBuildStep {

    @DataBoundConstructor
    public GParamsStep() {}

    @Override
    public void perform(
            @NonNull Run<?, ?> run,
            @NonNull FilePath workspace,
            @NonNull EnvVars env,
            @NonNull Launcher launcher,
            @NonNull TaskListener listener)
            throws InterruptedException, IOException {
        listener.getLogger().println("GParams perform called.");
        File file = new File("myfile.txt");
        boolean fileCreatedSuccessfully = file.createNewFile();
        String absolutePath = file.getAbsolutePath();
        listener.getLogger().println("ABSOLUTE PATH: " + absolutePath);

        if(!fileCreatedSuccessfully) {
            listener.getLogger().println("ERROR: file was not created");
            throw new IOException();
        }


    }

    @Symbol("gparams")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }
    }
}
