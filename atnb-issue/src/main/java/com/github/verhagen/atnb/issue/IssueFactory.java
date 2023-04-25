package com.github.verhagen.atnb.issue;

import com.github.verhagen.atnb.issue.domain.Issue;

public interface IssueFactory {

    Issue create(String identifier);
    Issue create(String identifier, String title);

    Issue create(String prefix, long number);
    Issue create(String prefix, long number, String title);
}
