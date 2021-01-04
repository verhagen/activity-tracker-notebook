# Progress

- [x] Implement loading of project data (from files `projects-*.hjson`) for activity identifiers
- [x] Implement loading of book data (from files `publisher-*.hjson`) for activity identifiers
- [ ] Create 'snapshot' from data: projects-\*.hjson, books-\*.hjson, etc and activity-logbooks.csv / md  
      Each snapshot should work, give correct results.
      
- [ ] Rename Identifiers like `project.mazes-for-programmers` into `project.mazes-for-programmers-java`  
      Update all ActivityLogbook entries and ActivityLogEntries since last snapshot
      Record rename(s) activities.


## IdentifierRegistery
- [x] Add IdentifierRegistery with search(query)
- [ ] IdentifierRegistery - show most relevant on top (last used identifiers in Activity Logbook and / or Activity Log instances) 
- [ ] IdentifierRegistery - filter out certain identifiers, because they are no longer relevant.  
      For Example: `client-abc.*`
      

## ActivityLogbook
- [ ] Validate identifiers 'project.project.abc' against IdentifierRegistery
- [ ]


- [ ] Create ActivityLogEntry every Activity has a start and stop moment (on a certain day)
- [ ] Register Tags, search by tag name(s)
