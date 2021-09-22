# The Fork Test

Android app to show details of a restaurant from the API of The Fork

### Achitecture and Libraries used

This project was done using Clean Architecture and MVVM. The main libraries used are:

- Hilt
- Retrofit
- JUnit
- Coroutines / Flow

### Considerations

There were some problems / difficulties found along the way that made it more difficult to produce an app that's like the designs sent. Some of them were: 

- Only 1 restaurant returned data (at least from the examples given)
- The menu is returned empty, which complicated a lot what to show in the screen
- The name of the chef also came as `null`, also making it hard to follow the provided screenshot
- The average price was nowhere to be found, so also this made things more difficult
- No prices where included in the API response (only minimum price which is not used in the screen)
- Couldn't find the food considerations
- Spent too much time trying to figure out which fields were which parts of the app. That added a lot of lost time.

Taking all this into consideration, I added some fields that I considered important, using a simple design the I selected. I know that it's not a very pretty one, and could be improved with more time, but I wanted to make it as practical as possible.

I tried to not go over the 4-5 hours they told me in the email. This made that some sacrifices were made. Some of them were:
- Not using ViewBinding that is the way views should be managed now.
- The error management and loading state are simple, just a loading spinner and a snackbar that allows a retry
- Animations could be improved, but also this would take time
- Styles are basic, could be improved
- There could be more tests done, just made a few to show how they would look like.

### How to run

First of all, you will need to add `api.key=YOUR_API_KEY` to your local.properties in order for the app to be built.

Also, you will need Java 11 to be able to run the app.

There are 2 ways to run this app:
1. Open the project with `Android Studio` and run it
2. Run `./gradlew assemble` with Java 11 installed, and then find the APK at (`app/build/outputs/apk`)

And also, to run the test run `./gradlew test`

### Final thoughts

I think that with more time, the final app could be better. If there's something in particular that you want me to implement, I'm happy to do it. 