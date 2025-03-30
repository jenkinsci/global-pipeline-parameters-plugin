package io.jenkins.plugins.gparams;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class UtilsTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    final String name = "Bobby";

    @Test
    public void testIsValidParameter() throws Exception {
        Assert.assertTrue(Utils.isValidParameterName("mypar"));
        Assert.assertTrue(Utils.isValidParameterName("validlongparamter0000000000000" + "validlongparamter0000000000000"
                + "validlongparamter0000000000000"
                + "validlongparamter0000000000000" + "0123456"));
        Assert.assertFalse(Utils.isValidParameterName(""));
        Assert.assertFalse(Utils.isValidParameterName("adfn/"));
        Assert.assertFalse(Utils.isValidParameterName("adfn.."));
        Assert.assertFalse(
                Utils.isValidParameterName("validlongparamter0000000000000" + "validlongparamter0000000000000"
                        + "validlongparamter0000000000000"
                        + "validlongparamter0000000000000" + "01234567"));
    }
}
