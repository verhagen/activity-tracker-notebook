package com.github.verhagen.atnb.issue;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;
import java.nio.file.Paths;

import com.github.verhagen.atnb.issue.domain.Issue;
import org.junit.jupiter.api.Test;


public class JiraIssueFactoryTest {

    @Test
    public void test() {
        URI issueHost = URI.create("https://jira.company.com/");
        JiraIssueFactoryConfig cfg = new JiraIssueFactoryConfig.Builder()
                .addOrganisation("github")
                .addProject("docs")
                .addIssuePrefix("github-docs")
                .addIssuePath(Paths.get("build/atnb/issue"))
                .addIssueServer(issueHost)
                .create();
        IssueFactory issueFactory = new JiraIssueFactory(cfg);

        Issue issue = issueFactory.create("12");

        assertEquals("GITHUB-DOCS-12", issue.getIssue());
        assertEquals("https://jira.company.com/browse/GITHUB-DOCS-12", issue.getIssueUri().toString());
        assertNull(issue.getTitle());
    }

}
