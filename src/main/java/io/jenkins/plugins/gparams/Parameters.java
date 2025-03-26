package io.jenkins.plugins.gparams;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import jenkins.model.Jenkins;

/**
 * Common parameters
 */
@SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
public class Parameters {
    static final String JenkinsHomeDir =
            Objects.requireNonNull(Jenkins.getInstanceOrNull()).getRootDir().getAbsolutePath();
    static final String GParamDirectoryName = JenkinsHomeDir + "/gparams/";

    // Length of parameter 'name' in gpRead and gpWrite steps
    static final int NameLen = 128;
}
