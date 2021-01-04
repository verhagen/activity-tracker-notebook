# Activity

An activity consists of four things:

- :date: When  
  This is required. It represents a moment in time. On which day took the activity place.
- :id: Identifier  
  This is required, and identifies where the activity is about.
- :hourglass: Duration  
  This is not required, but is very important! This tells how much time is spent on the activity.
- :clipboard: Description  
  This is optional and can be used to give some more background about the activity.


### When

When writing down activities, this is mostly done on a daily base. This makes it easy to remember as well as to see, if
a new entry was already made for a particular day.

possible to easily look up
when time was spent on what activities.

To make 
To know when time was spent on a certain activity, it's required to know 

Although the duration is most important, the moment in time is also relevant. So when spending time on a activity, it is
important on which day that was done! Each activity should therefore record 


## Activity Identifier

An activity must have an activity identifier. Some examples of an activity could be: `cooking`, `working`, `shopping`, `study`.
These are very generic activities, which is OK, but to make them more useful a more specific identifier is better.
Maybe one like to read back what was cooked on a certain moment. Maybe it was 'goulash' or 'spätzle', these identifiers
would become `cooking.goulash` and `cooking.spätzle`. This way one can find back when what was cooked, but also how many times,
it has been cooked this month,  this year or in the last 90 days.

But maybe it's interesting to know which cooking recipe has been used, in case different goulash recipes are tried out.
Or different some different recipes are used again and again, but one likes too know which recipes have been cooked how many
times.

For studying a certain subject, one is probable reading a particular book. Reading the complete book, probably takes multiple
moments. Each of these moments can be seen as an activity item. Maybe one likes to record which chapters is being red?

`reading.book.mythical-man-month.chapter-6`


## Activity Duration

Each activity takes an amount of time. When this is added to the activity, it's possible to see how much time is spent on
an activity, every day, or in total per week or per month. Or compare which cooking recipes take less time or which ones take
more time. Or see if one repeats a certain activity like cooking a specific recipe, that it will be taking less time? Or
finding out the progress of reading a particular book.

Duration of an activity can be given in hour and minutes, using the format `<hours>:<minutes>`. A shortcut us just entering
`<hours>` or only `:<minutes>`. Some examples:

`:30` - 30 minutes 
`1:30` - 1 hour and 30 minutes
`:90` - 90 minutes, could have been entered as `1:30` as well.

In the end the time spent on an activity is the most important part. But maybe it's also interesting to record when the
activity started and stopped. In that case use a time stamp, for starting and stopping the activity. Beginning and ending
an activity can be given in `hour:minutes`.

`9:30-10:15` - Started activity at 9:30 and ended activity at 10:15 in the morning.
`17:45-18:30` - Started activity at 17:45 and ended activity at 18:30 in in afternoon / evening.




## Activity Description

Just an additional description. Useful when reading back later.


## Some Examples

To make recording activities quick, easy and readable and not related to a specific tool, they can be stored as plain text.

- `:45 reading.book.mythical-man-month.chapter-6` - Reading the book, for 45 minutes.
- `9:30-10:15 reading.book.mythical-man-month.chapter-6 Half way the chapter.` - Being more specific, when the time was spent.
- ``
- ``
- ``
- ``