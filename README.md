# Multi-threaded Client Server App

This is a Multithreaded Client/Server app that uses JAVA Sockets to provide TCP communication between a client and the server. The server uses JBDC to connect to the database and perform all client queries.

![UML Diagram](https://github.com/Esedicol/JAVA-Sockets/blob/master/frames.png)

# How to use the app

When you run the app two frames will pop up, the server frame and a frame that allows you to create a client. When a new client is created you will be prompted a new JFrame to login, the server then performs SQL queries to validate if your input exists in our databse. If user is found you will be able to click around the app and send requests to the server.


Every requests made by the client is printed in the Server frame and each printed logs is labeled by the client name so that we know who requested that query. The app maily displays a list of students in our databse and the user logged in can do the following:
- [Next button] - display the next student 
- [Previous Button] - display the previous student
- [Search Button] - search for a user by last name and display that user
- [Clear Button] - clears every fields
- [Logout Button] - sign out the user and returns to the login frame

# UML Class Diagram

![UML Diagram](https://github.com/Esedicol/JAVA-Sockets/blob/master/UML.png)
