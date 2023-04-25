package com.github.verhagen.atnb.issue;

import com.github.verhagen.atnb.issue.domain.Issue;
import com.github.verhagen.atnb.issue.domain.JiraIssue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JiraIssueFactory implements IssueFactory {
    private final JiraIssueFactoryConfig cfg;
    private final Pattern patternPrefixWithNumber = Pattern.compile("^(\\w+)-(\\d+)$");
    private final Pattern patternOnlyNumber = Pattern.compile("^(\\d+)$");

    public JiraIssueFactory(JiraIssueFactoryConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public Issue create(String identifier) {
        return create(identifier, null);
    }
    @Override
    public Issue create(String identifier, String title) {
        Matcher matcher = patternPrefixWithNumber.matcher(identifier);
        if (matcher.matches()) {
            String prefix = matcher.group(1);
            String number = matcher.group(2);
            return create(prefix, Long.valueOf(number).longValue(), title);
        }
        matcher = patternOnlyNumber.matcher(identifier);
        if (matcher.matches()) {
            String number = matcher.group(1);
            return create(cfg.getIssueMainPrefix(), Long.valueOf(number).longValue(), title);
        }
        throw new IllegalArgumentException("Argument 'identifier' with value '" + identifier + "' should be a number.");
    }
    @Override
    public Issue create(String prefix, long number) {
        return create(prefix, number, null);
    }
    @Override
    public Issue create(String prefix, long number, String title) {
        if (! cfg.getIssuePrefix().contains(prefix.toUpperCase())) {
            throw new IllegalArgumentException("Argument 'prefix' with value '" + prefix + "' is not a known issue prefix. Known prefix values are " + cfg.getIssuePrefix());
        }
        return create(cfg.getOrganisation(), cfg.getProject(), prefix, number, title);
    }
    public Issue create(String organisation, String project, String issuePrefix, long number, String title) {
        return new JiraIssue.Builder().addOrganisation(organisation).addProject(project).addPrefix(issuePrefix).addNumber(number)
                .addTitle(title)
                .addIssueServer(cfg.getIssueServer())
                .create();
    }
}
