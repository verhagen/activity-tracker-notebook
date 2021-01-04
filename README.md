# Activity Logbook

:notebook: :date: :hourglass: :mag:

## The Idea

This project is about logging activities. In a simple and easy way. So one can use them later to see when was time spent
on a certain activity and how much time was spent, that day / week / month, on that activity.

**Fundamental Ideas:**
- Easy to use
- Log entries should be easy to create / edit / read and understand


The most important element is the [Activity](src/documentation/activity.md)!


## Background

:watch: **Time Consuming**
Many times one need to records certain activities. Like working on a particular project and / or task. Many time tracking
systems are quit time consuming in registering starting / stopping a task or entering the duration. Because it's so time
intensive and not quick (user friendly), many people don't like to do it. Or when required only do it once a day / week /
month.

:computer: **Multiple Systems**
It would not be the first time that entering the same kind of activity data is required, and no easy extraction from 
one system and upload in to another system is possible, if at all.

:necktie: **Multiple Clients / Broker**
Maybe one works for multiple clients, and need to know in some  point in the near future, how many time was spent for a
certain client. To create and sent the invoice. But also need to record these hours spent, in the client specific system.

Or maybe you work through a broker, which probably also requires that hours spent on a certain client / project are
entered in their system.

:briefcase: **Book Keeping**
Maybe you're required for government to record all hours worked in a year. And doing administrative tasks or exploring
potential new opportunities might count as working hours, although you can't sent a invoice for it.

:mag: **Historical Search**
When keeping an easy to read activity logbook, it should be easy to find back historical information.


## Current Status

See the [progress](progress.md).


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
    