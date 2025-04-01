package io.jenkins.plugins.gparams;

import org.apache.commons.lang3.StringUtils;

// Common methods and variables used in project.
public class Utils {
    public static boolean isValidParameterName(String name) {
        return StringUtils.isAlphanumeric(name) && name.length() < Parameters.NameLen;
    }
}
