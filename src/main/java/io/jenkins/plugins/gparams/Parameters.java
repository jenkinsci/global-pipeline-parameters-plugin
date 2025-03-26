package io.jenkins.plugins.gparams;

import jenkins.model.Jenkins;

/**
 * Common parameters
 */
public class Parameters {
    static final String JenkinsHomeDir = Jenkins.get().getRootDir().getAbsolutePath();
    static final String GParamDirectoryName = JenkinsHomeDir + "/gparams/";

    // Length of parameter 'name' in gpRead and gpWrite steps
    static final int NameLen = 128;
}
