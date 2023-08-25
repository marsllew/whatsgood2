This project was an attempt at making a dining services app to serve the students at the college of William & Mary better than the offerings from the current company. It was a group project with developers of varying levels of coding ability, and most code made by other students is left unaltered. While I did do bugfixing on other sections of code I generally avoided rewriting large sections of code written by other students, below will be an overview of what I worked on. As well the app originally webscraped the dining service providers website to pull the dining information. Unfortunatly W&M has switched their dining service provider and our method of data collection is no longer viable. To compensate for this I hardcoded some menu options in, so that the app could still function and act as a demo while I wait for the new menu system to come completely online and change over the method of data collection.

The app worked by using the mentioned webscraper to pull the current menu and display it, it then allowed users to leave reviews and favorite menu options. This allowed general scores to be displayed and let anyone read any review. As well the app would send a notificaiton when it was open to let the user know that something they had favorited was being served at one of the dining halls.

If you want to use the app there is a default login so you don't have to enter any personal information, just put the letter a in both the username and password slot and it will login to a premade account. If you have any questions about the code please do not hesitate to reach out and ask.

Code I worked on
- Login page and all associated functions
- Review display system
- Most of the work on the system to leave reviews
- Entire storage and retrieval system for reviews
- Refactored the base data structure to allow for access to one masterlist of the menu in the program
- Moved the webscrapers to run on background threads upon start to get rid of user delay upon selecting a location
- In general bugfixes to both the barebones notification system and the general display systems in the app


Everything below this line is the original README from when the project was submitted.

# What's Good?

## Project Description
What’s Good? is a way for W&M students to take control of their dining experience through community-sourced reviews and a detailed view of hours, offerings, traffic, and nutritional information for the on-campus dining options. 

## Technologies Used
Android Studio

## References
To be determined
