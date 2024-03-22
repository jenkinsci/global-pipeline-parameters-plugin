package io.jenkins.plugins;

/**
 * Common parameters
 */
public class Parameters {
    static final String JenkinsHomeDir = System.getProperty("JENKINS_HOME");
    static final String GParamDirectoryName = JenkinsHomeDir + "/gparams/";

    // Length of parameter 'name' in gread and gwrite steps
    static final int NameLen = 128;
}
