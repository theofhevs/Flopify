# Project description

This application enables streaming of MP3 audio between two clients. It consists of a server and multiple clients. Clients can connect to the server, add music in MP3 format, and list available music on the server (only informaton about the music is stored on the server, not the music itself). Once a music choice is made, the client connects to the music provider (another client), and streaming begins. After the music finishes or when the user stops it, the client returns to the server menu.

## Features

- Add music
- List available music
- Stream music (after listing the music available)
- Disconnect from server
- List clients connected to the server

## How to use our application

Launch client.jar
...

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
1: test.mp3, Port: 65126
2: The pharaoh s curse-.mp3, Port: 65310
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
Port to connect : 45001
Establishing a connection with 65532
Press 'S' to stop the music, 'P' to pause or resume the music
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
There are 2 clients connected to the server
Client 1:
IP address: /127.0.0.1
Port: 61395
Client 2:
IP address: /127.0.0.1
Port: 61418
```

## Connection with other part of the modules

To manage the music list and the clients connected, we've used an ArrayList seen earlier in the collaborative programming course.

We also used the command pattern to manage the various commands on the menu (add music, list available music, and disconnect from server).

## Add-ons 

To ensure that only one instance of the Server is active at the same time, we used the singleton pattern. It simplified the way we handle the Server.

We've also added the ability to pause and resume the music, or to stop it, which takes the client back to the server's main menu.


Finally, we used the logs on the Server side, which allowed us to debug and troubleshoot easily, as well as providing detailed information about the errors.

## Our group 

- Jonathan Araújo 
- Théo Falcinelli
