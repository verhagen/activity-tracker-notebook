# Activity Logbook

The [Activity](activity.md) Logbook contains records of activities.

> **Logbook Background**
>
> A (logbook)[https://en.wikipedia.org/wiki/Logbook] (a ship's logs or simply log) is a record of important events in the management, operation, and navigation of a ship. It is essential to traditional navigation, and must be filled in at least daily.


## Activity Logbook Commands

- `identifier  start  <time|-hours:minutes>`  
  Start a new activity, at current moment.
  Optional a time stamp, or correction in hours and minutes.  
  | time | -hours:minutes | description |
  | ---- | ---- | ---- |
  | 9:00 | -1h  | |
  |      | -20m | This activity is already started 20 minutes earlier. |
  

- `identifier  stop  <time|-hours:minutes>`  
  Stop the activity, at current moment. The activity should have been started, but not stopped.
  Optional a time stamp, or correction in hours and minutes.  

- `stop`  
  Stop last started activity

**General Rules**

If an activity is started, and again started, nothing happens.
If an activity is stopped, and again stopped, nothing happens.
If an activity X is started, and a new activity Y is started, then previously started activity X is stopped, and the new activity Y is started.

- `status`  
  Shows the current running activities
  Shows the suspended activities

- `identifier  start-parallel`  
  Start a new activity, at current moment. If another activity is running, it will not be stopped, as the normal `start` would do.

- `identifier switch`
  Put current activity on hold. Start given activity.

    halt
    identifier start
  
- `identifier halt`  
  Put given activity on hold. With the intention to continue (resume) the activity later today.

- `identifier resume`  
  If a current activity is active, stop it. Resume the given activity.

- `halt`  
  Halt current activity
- `resume`
  If a current activity is active, stop it. Resume last activity which is put on hold.

