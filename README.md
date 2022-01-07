# Async Chat

This mod creates a thread dedicated to handling and processing chat messages. 
This removes blocking operations from the main thread which may cause client rendering lag.  

## Technical
On every chat message received, Minecraft checks if the player UUID is blocked. The list of blocked UUIDs is retrieve from a Minecraft web service API and then cached locally to prevent subsequent fetches. If the UUID is found in the list, the message is thrown away.  
The fetch request is a blocking operation, and the thread handling the message must wait for a full response from the web server. Because the message is being handled in the main game loop, if a fetch request is initiated, the game can not render until the message has been fully processed. 

A blocklist fetch is done on chat message if the blocklist isn't loaded and it's been more than 120 seconds since the last fetch attempt. As of 1.18, the blocklist is also fetched on connect.

This has largely not been that big of an issue because it happens only once per connection, however, as of 2022-01-06 the web server providing the blocklist does not appear to be functioning properly and so the game is failing to retrieve the blocklist. This causes the game client to noticeably lag every 2+ minutes whenever someone sends a message in chat.

I have written a mod which disables the blocklist fetch, [no-blocklist-lookup](https://github.com/totorewa/no-blocklist-lookup). Alternatively, this mod does not disable the blocklist fetch, but instead offloads the message handling to another thread so that that thread can be blocked instead of the main thread.
