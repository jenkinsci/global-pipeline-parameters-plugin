package io.jenkins.plugins.gparams;

import jenkins.model.Jenkins;

import java.util.Objects;

/**
 * Common parameters
 */
public class Parameters {
    static final String JenkinsHomeDir = Objects.requireNonNull(Jenkins.getInstanceOrNull()).getRootDir().getAbsolutePath();
    static final String GParamDirectoryName = JenkinsHomeDir + "/gparams/";

    // Length of parameter 'name' in gread and gwrite steps
    static final int NameLen = 128;
}
