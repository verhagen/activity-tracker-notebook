# Ideas - Commands



## List

- `list` - List the current identifiers  
  `issue  list` - list the issue identifiers
  

## History

- `history` - Show the history (default with last # days / number of activities)  
  `book history` - Show history of book activities (reading)
  `<organisation> history (this|last) (week|month|sprint)` - Show the history of activities, related to an organisation...


## Generate

- `generate overview (<year>.w##|<year>.m##)`  
  `<organisation> generate overview (<year>.w##|<year>.m##)`  
  

## Reading Book / Article

- `article  start  <uri>`
- `article.<id>  start|stop`
- `article  start|stop|finish  [<title>]\(<uri>)` automatically generate identifier, based on uri -> host_article-id

## Proof of Concept

- `poc.<identifier>  (start|stop)`
- `poc.<identifier>  init`  Create poc directory, under $poc_root} or if not defined ${scm_root}/poc/<identifier>  
  Now a `gradle init` as well as an `git init` can be run there.
  Use https://start.vertx.io/
