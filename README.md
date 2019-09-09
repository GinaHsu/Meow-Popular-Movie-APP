# Meow Popular Movie APP: 
This is an Android app for the popular movies searching.
https://ginahsu.com/portfolio/meow-popular-movie-app/


## Udacity course: Associate Android Developer Fast Track: 
https://www.udacity.com/course/associate-android-developer-fast-track--nd818

## Project 1:
  Required Tasks
  Build a UI layout for multiple Activities.
  Launch these Activities via Intent.
  Fetch data from themovieDB API

## Project 2:
Add additional functionality to the app we built in Stage 1.

  We add more information to our movie details view:
  - allow users to view and play trailers ( either in the youtube app or a web browser).
  - allow users to read reviews of a selected movie.
  - allow users to mark a movie as a favorite in the details view by tapping a button(star). This is for a local movies collection that we maintain and do not require an API request*.
  - modify the existing sorting criteria for the main view to include an additional pivot to show their favorites collection.
  
------------------------------------------------------------------------------------------------------------------------

Note: For NetworkUtils.java, I stored the API key in the file- gradle.properties, that is a local file loacated in C:\Users\xxx\.gradle (xxx is your user name), is not uploaded to the version control.

How to run the movie app?
Create a file named gradle.properties in your local(C:\Users\xxx\.gradle (xxx is your user name)) and added a line, MyMovieDBApiKey="XXXXX", in that file. Then during build time, Gradle will generate the BuildConfig file to store some build-related constants. 

More information:
https://stackoverflow.com/questions/33134031/is-there-a-safe-way-to-manage-api-keys/34021467#34021467
https://stackoverflow.com/questions/35722904/saving-the-api-key-in-gradle-properties

