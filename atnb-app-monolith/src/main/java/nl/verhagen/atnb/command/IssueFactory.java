package nl.verhagen.atnb.command;

public interface IssueFactory {

    Issue create(String identifier);
    Issue create(String identifier, String title);

    Issue create(String prefix, long number);
    Issue create(String prefix, long number, String title);
}
