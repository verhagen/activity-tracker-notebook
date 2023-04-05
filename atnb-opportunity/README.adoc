# Activity Logbook - Opportunity


## Usage

- 


## Ideas

- Create opportunity directory
    
    <$activity-data>/opportunity/
        <date>-<agent>_<organisation>_<title>/
        		content.md

- Create contacts directory

    <$activity-data>/opportunity/contacts/
        <agent>/
        		contacts.md

File `content.md`:

    # <agent> - <organisation> - <title>
    Location: Utrecht
    Status:
    	
    ## Actions
    
    ## Contacts
    	
    - Jeffrey Hesta (Computer Futures)
    	
    	
    ## Details
    	
    - Multi scrum team
    - Java Spring Boot
    - Java RS / XS ?
    - MongoDB
    - Angular
    - SQL
    - Maven
    	
    ## Logbook
    	
    ### 2021.12.20 Monday
    	
    - Phone call with Jeffrey about the 4 opportunities
    - Mail [Volksbank, Powerhouse, Douane en DPG Media voor de rol van Sr. Java Developer](https://mail.google.com/mail/u/0/#inbox/FMfcgzGllVphRlLpffSXdLrGvzCrgLjz)
	
File `contacts.md`:

	# Contacts


## Examples

	app  opportunity  create  agent; organisation; title
	app  opportunity  create  2021.12.20; agent; organisation; title
	app  opportunity  create  2021.12.20; agent; organisation; title; http://uri-to-mail.org/
	