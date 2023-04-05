# Activity Event Creator / Activity (Logger) Command / Activity Tracker / Notebook

## Ideas

Maybe the _Activity Event Creator_ should be separate from the content creation.

The _Activity Events_ should be logged into a file, or directory send to the 


## Decisions to Make

- What to do with MarkDown or AsciiDoc links?  
  Inside the text part of an ActivityTracker links as used in MarkDown or AsciiDoc, can be a nice addition.  
  This way it will be possible to write the text part as:  

    <title>; <uri>                // 2 ordered fields
    title: <title>; uri: <uri>    // 2 named fields separated by semi-colon ';'
    title: <title> uri: <uri>     // 2 named fields separated by space(s) ' '
    [<title>](<uri>)              // A title and uri field, represented in MarkDown format
    <uri>[<title>]                // A title and uri field, represented in AsciiDoc format

  Maybe this is difficult if there are multiple links in MarkDown or AsciiDoc format  
  `LinkTextField(StringTextField(title), UriTextField(uri))`

- Maybe field extraction
 
 
## Action List

- [ ] Create `IssueTaskHandler`
- [ ] Create `JiraIssueTaskHandler`
- [ ] Check if PrimaryIdentifiers require unique identifier information
- [ ] Add test cases to see that only known command / task are allowed
- [ ] Improve the `App` and `AppRunner` test cases as it is the entry point for the cli application.  
      A good example of what is allowed with commands and related fields  
      and what not, especially with the MarkDown, AsciiDoc links  
      (and text links (text links: '<some title> <uri>') maybe that should be completely skipped, or only allowed at front of a text block?) 
      

- [ ] Add field extraction  
      Each activity should have the format:  
      app  identifier  command  <remainer>  
      This remainer can have key / value pairs (fields) which should be easy to extract.
- [ ] Add possibility for template engine(s) next to current StringBuilder implementation
- [ ] Create initial structure / configuration
- [x] Make executable jar  
      Add `maven-jar-plugin`
- [x] Make fat executable jar  
      Add `maven-assembly-plugin`

## Investigate
- [ ] Look into `git-commit-id-maven-plugin`
- [ ] Look into `maven-git-versioning-extension`


## Ideas About Usage

### Initialization

    app  init

### Project

	app  project  start|stop
	app  project  add  
	app  project.mazes-for-programmers  rename  mazes-for-programmers-java

### Book

    app  book  add  

### Opportunity

    app  opportunity  create  [date] <agency> <organisation> <role>
    app  opportunity  list
    app  opportunity  list  <query>
    app  opportunity.tennet  list      List all opportunities, with 'tennet' in their identifier

    app  opportinity.2021.12.20  start
    app  opportinity.2021.12.20  stop

    app  opportunity  create  <date;> agent; organisation; title
    app  opportunity  create  2021.12.20; agent; organisation; title
    app  opportunity  create  2021.12.20; agent; organisation; title; http://uri-to-mail.org/


## Ideas about direction

- [The 6 best note-taking apps of 2022](https://zapier.com/blog/best-note-taking-apps/)
