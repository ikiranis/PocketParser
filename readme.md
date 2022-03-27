# Pocket parser

This app is using the Pocket's [API](https://getpocket.com/developer/docs/overview) to retrieve all the
bookmarks and parse only the urls. Then you can delete all from Pocket.

**The app is still in development**

![2022-03-28_00-49](https://user-images.githubusercontent.com/50238022/160302684-a41bb5d8-f18e-475d-af0f-9417d7b3b6b7.png)

### How it works

1. Uses the OAuth 2.0 for [authentication](https://getpocket.com/developer/docs/authentication)
2. If app is authenticated, user can [retrieve](https://getpocket.com/developer/docs/v3/retrieve) all the bookmarks
3. In textarea, all the bookmarks urls displayed in simple text
4. User can [delete](https://getpocket.com/developer/docs/v3/modify) all the bookmarks in Pocket

### Technical info

The code is written in Java 15, JavaFX and it is a Maven Project. If you want to compile it, you have
to get the [consumer key](https://getpocket.com/developer/apps/new) from Pocket. 
Then you must copy .env.sample to .env file and edit this with the consumer key. 

The compiled version (you can download it), already has the consumer key and you don't have to do anything.

### Running (temporary)

You can run the first released version of jar file. In the first time, you must choose "Get Access" to 
grant access from Pocket.
After that (every time you run the app) you will choose only "Get Bookmarks" or "Delete Bookmarks" 
(after getting the bookmarks). 

This is the first version. Needs more work in interface (make things more automated) and improve the code
to make the security checks and validations.

