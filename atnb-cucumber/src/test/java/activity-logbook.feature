#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file

@tag1
Scenario: Starting and stopping an activity
  Given An ActivityLogbook at 2020.01.02
  And Activity 'reading.book.mazes-for-programmers' is started at 9:00
  When Activity 'reading.book.mazes-for-programmers' is stopped at 12:00
  Then ActivityLogbook should contain 1 entry for 2020.01.02
  And ActivityLogbook should contain identifiers ['reading.book.mazes-for-programmers']

Scenario: Starting, stopping an activity. Then start again that activity.
  Given An ActivityLogbook at 2020.01.02
  And Activity 'reading.book.mazes-for-programmers' is started at 9:00
  And Activity is stopped at 12:00
  When Activity 'reading.book.mazes-for-programmers' is started at 13:00
  Then ActivityLogbook should contain 2 entry for 2020.01.02
  And ActivityLogbook should contain identifiers ['reading.book.mazes-for-programmers']

Scenario: Starting, stopping an activity. Then start and stopped again that activity.
  Given An ActivityLogbook at 2020.01.02
  And Activity 'reading.book.mazes-for-programmers' is started at 9:00
  And Activity 'reading.book.mazes-for-programmers' is stopped at 12:00
  And Activity 'reading.book.mazes-for-programmers' is started at 13:00
  When Activity is stopped at 16:00
  Then ActivityLogbook should contain 2 entry for 2020.01.02
  And ActivityLogbook should contain identifiers ['reading.book.mazes-for-programmers']

Scenario: Starting activity. And start again next day.
    This should actualy stop the activity of the previous day.
  Given An ActivityLogbook at 2020.01.02
  And Activity 'reading.book.mazes-for-programmers' is started at 9:00
  And next day
  When Activity 'reading.book.mazes-for-programmers' is started at 10:00
  Then ActivityLogbook should contain 1 entry for 2020.01.02
  And ActivityLogbook should contain 1 entry for 2020.01.03

Scenario: Starting activity. Switch to other activity. Stop Activity. And resume activity.
  Given An ActivityLogbook at 2020.01.02
  And Activity 'reading.book.mazes-for-programmers' is started at 9:00
  And Activity 'programming.project.mazes-for-programmers' is switched to at 10:00
  When Activity is stopped at 12:00
  Then ActivityLogbook should contain 2 entry for 2020.01.02
  And ActivityLogbook should contain 1 on-hold activities

#@tag2
#Scenario Outline: Title of your scenario outline
#  Given I want to write a step with <name>
#  When I check for the <value> in step
#  Then I verify the <status> in step
#
#  Examples: 
#    | name  | value | status  |
#    | name1 |     5 | success |
#    | name2 |     7 | Fail    |
