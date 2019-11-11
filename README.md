# Multi-threaded Client Server App

This is a Multithreaded Client/Server app that uses JAVA Sockets to provide TCP communication between a client and the server. The server uses JBDC to connect to the database and perform all client queries.

![UML Diagram](https://github.com/Esedicol/JAVA-Sockets/blob/master/frames.png)

# How to use the app

When you run the app two frames will pop up, the server frame and a frame that allows you to create a client. When a new client is create you will prompted a JFrame to login, the server then performs SQL queries to validate if that user exists in our databse.


Every requests made by the client is printed in the Server frame and each printed logs is labeled by the client name so that we know who requested a query to the server. The app maily displays a list of students in our databse and the student can do the following:
- [Next button] - display the next student 
- [Previous Button] - display the previous student
- [Search Button] - search for a user by last name and display that user
- [Clear Button] - clears every fields
- [Logout Button] - sign out the user and returns to the login frame
