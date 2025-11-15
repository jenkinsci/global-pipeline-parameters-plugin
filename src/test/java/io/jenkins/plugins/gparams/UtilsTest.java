package io.jenkins.plugins.gparams;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
class UtilsTest {

    private JenkinsRule j;

    @BeforeEach
    void beforeEach(JenkinsRule rule) {
        j = rule;
    }

    @Test
    void testIsValidParameter() {
        assertTrue(Utils.isValidParameterName("mypar"));
        assertTrue(Utils.isValidParameterName("validlongparamter0000000000000" + "validlongparamter0000000000000"
                + "validlongparamter0000000000000"
                + "validlongparamter0000000000000" + "0123456"));
        assertFalse(Utils.isValidParameterName(""));
        assertFalse(Utils.isValidParameterName("adfn/"));
        assertFalse(Utils.isValidParameterName("adfn.."));
        assertFalse(Utils.isValidParameterName("validlongparamter0000000000000" + "validlongparamter0000000000000"
                + "validlongparamter0000000000000"
                + "validlongparamter0000000000000" + "01234567"));
    }
}
