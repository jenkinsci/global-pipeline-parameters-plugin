package io.jenkins.plugins.gparams;

import hudson.model.FreeStyleProject;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class GParamsReadStepTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    final String name = "Bobby";

    @Test
    public void testNothing() throws Exception {
        FreeStyleProject project = jenkins.createFreeStyleProject();
        project = jenkins.configRoundtrip(project);
    }
}
