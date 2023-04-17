package nl.verhagen.atnb.command;

import java.net.URI;
import java.util.List;

public interface Issue {
    List<String> getIdentifier();

    String getOrganisation();

    String getProject();

    String getIssue();

    URI getIssueUri();

    String getTitle();
}
