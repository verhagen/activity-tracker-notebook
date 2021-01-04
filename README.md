# Activity Logbook

## The Idea

This project is about logging activities. In a simple and easy way. So one can use them later to see when was time spent on a certain activity and how much time was spent, that day / week / month, on that activity.

**Fundamental Ideas:**
- Easy to use
- Log entries should be easy to create / edit / read and understand


The most important element in this project is the [Activity](src/documentation/activity.md)!

- [Progress](progress.md)


## Project Plan

- Make (per day) multiple entries in the Activity Logbook.
- Use the Activity Logbook, to extract information like:
  - time spent on certain activity / activities
  - time spent for certain client
  - time spent on self study
  - time spent reading certain book
  - time spent reading books, this month
  - time spent on a specific activity this day / week / month / year / overall
  - where has time been spent on a certain day / week / month / year

- Quick and easy way to start / stop working on activities  
  Activity Log Entry instance have:
    - TimeStamp begin
    - TimeStamp end
    - BreadCrumbPath

- 



## Thoughts

- Should (certain) Activities (Tasks / Issues) have a Status (like: Open, Closed,  ...)?
  If closed, make them less relevant in search
- Use commands for everything, like:
    - add new project
    - add new book
    - rename identifier
    - add identifier
    - deactivate identifier (no longer available for activity)
    - remove identifier (no longer available for activity)
    