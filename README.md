# Multi-threaded Client Server App

This is a Multithreaded Client/Server app that uses JAVA Sockets to provide TCP communication between a client and the server. The server uses JBDC to connect to the database and perform all client queries.

# How to use the app

When you run the app two frames will pop up, the server frame and a frame that allows you to create a client. When a new client is create you will prompted a JFrame to login, the server then performs SQL queries to validate if that user exists in our databse.


Every requests made by the client is printed in the Server frame and each printed logs is labeled by the client name so that we know who aslthe requests 

JBDC to allow the server to perform SQL queires to the database. The server can handle multiple requests from multiple clients and the server respose to the appropriate
