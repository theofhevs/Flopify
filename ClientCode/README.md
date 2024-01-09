# Project description

This application enables streaming of MP3 audio between two clients. It consists of a server and multiple clients. Clients can connect to the server, add music in MP3 format, and list available music on the server (only informaton about the music is stored on the server, not the music itself). Once a music choice is made, the client connects to the music provider (another client), and streaming begins. After the music finishes or when the user stops it, the client returns to the server menu.

## Features

- Add music
- List available music
- Stream music (after listing the music available)
- Disconnect from server
- List clients connected to the server

## How to recompile and run our application

1.  Open the ClientCode and ServerCode folders in 2 separate Eclipse pages. The following steps must be completed once for ClientCode and once for ServerCode.

2. Right-click on the root folder (ServerCode or ClientCode) and select Run As -> Maven Build.

3. Once on the Edit Configuration page, write 'clean install' in Goals and click on Run.

4. Click on Project in the upper tabs, click on Update Maven Project (or Alt + F5) and click on OK. 

5. Two jar files will be created. The one we're interested in starts with VSFy.

Once the jar files have been created for both server and client, open a new terminal in the folder where the jar of the server is located and do this command
```cmd
 java -jar .\VSFy-jar-with-dependencies.jar
```
Now, do the same to run the client (in another teminal where the jar of the client is located)
```cmd
 java -jar .\VSFy-jar-with-dependencies.jar
```

The client and the server should be running. You can go to the client side and discover the different features of the application.

## How to use our application

Initial menu with commands available
```cmd
=====Commands available=====
1       List available songs
2       Share a song
3       Disconnect and close
4       Who is connected ? 

Please enter a command number (1-4):
```

Once a number from 1 to 4 entered, the command will be executed.

### 1. List available songs

When this command is executed, the server will list the music files and the port used to connect to the client. 
It will then ask if the user wants to stream any music.

```cmd
1: test.mp3, Port: 259d1306-795f-4f68-9dc4-765fa141502b
2: The pharaoh s curse-.mp3, Port: d44a866d-3ee2-4878-91e9-db07ed9beece
Would you like to stream a song? (y/n) :
```
If the user chooses "n", the menu will be displayed again

If the user chooses to stream a track by selecting "y", he will be asked to enter the track number.
```cmd
Enter the number of the song you want to stream :
```
Once the music number has been entered, the stream will start.
The user can pause and resume the music by indicating "p", or stop it by indicating "s", which will return the user to the menu.

```cmd
Music path : C:\Users\theof\Desktop\ryanGosling.mp3
Music owner ID: 259d1306-795f-4f68-9dc4-765fa141502b
Establishing a connection with /127.0.0.1
Press 'S' to stop the music
```

### 2. Share a song

The Server will ask to enter for the path of the song
```cmd
Enter the full path of the song you want to share
Please enter the path of the music you want to share (must be a .mp3 file):
```
If the file does not exist in the specified path, or if the format is not MP3, the program will repeat its request
```cmd
The file does not exist
Please enter the path of the music you want to share (must be a .mp3 file):
```

If the indicated file exists and is in MP3 format, it will be added to the list of available music and this message will be displayed
```cmd
The music test.mp3 was shared with the server !
```

### 3. Disconnect and close

This command closes the connection between client and server
```cmd
Closing connection with the server
```
### 4. Who is connected ?

This command lists the clients connected to the server
```cmd
There are 3 clients connected to the server
1 : Client ID : 259d1306-795f-4f68-9dc4-765fa141502b
2 : Client ID : d44a866d-3ee2-4878-91e9-db07ed9beece
3 : Client ID : b6fcf242-0457-459a-8ff8-383a255394de
```

## Connection with other part of the modules

To manage the music list and the clients connected, we've used an ArrayList seen earlier in the collaborative programming course.

We also used the command pattern to manage the various commands on the menu (add music, list available music, disconnect from the server and display the list of connected clients).

## Add-ons 

To ensure that only one instance of the Server is active at the same time, we used the singleton pattern. It simplified the way we handle the Server.

We've also added the ability to stop the music, which takes the client back to the server's main menu.

With our code, a client can handle stream audio files to multiple clients simultaneously.

Finally, we used the logs on the Server side, which allowed us to debug and troubleshoot easily, as well as providing detailed information about the errors.

## Our group 

- Jonathan Araújo 
- Théo Falcinelli
