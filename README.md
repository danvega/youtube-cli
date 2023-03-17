# YouTube Stats CLI

A huge reason I love being a programmer is because I love to solve problems. More specifically I love to solve my
everyday problems. Part of job as a Developer Advocate is to track the content I create and the impact it has on the 
community. 

Last year I was tracking my YouTube videos in a spreadsheet in a very manual process. This also meant that some metrics 
like views on each video weren't being updated to reflect the current state of the video. I wanted to automate this 
process so that I could focus on creating content and not worry about tracking it.

I decided to create a CLI that would allow me to fetch the stats for my YouTube channel. This CLI is using Spring 
Shell and the YouTube Data v3 API. If you're interested in learning more about Spring Shell, check out my [Introduction
to Spring Shell](https://youtu.be/8B0IjOIzicU).

## Getting Started 

If you want to follow along and create your own YouTube CLI you will need to get an API key from Google. You can find 
instructions on how to do that [here](https://developers.google.com/youtube/v3/getting-started). Once you have that
you will need to add the following properties to your `application.properties` file.


```properties
youtube.channel-id=YOUR_CHANNEL_ID
youtube.key=YOUR_API_KEY
```

If you don't want to store those properties in a file you can also set them as environment variables or do what I did and
set them up in a file called secret.properties. If you run your application in the `dev` profile it will automatically
be picked up.

```properties
spring.config.import=optional:secret.properties
```

## Running the CLI

When those values are set you can run the CLI by running the following command or by running the `main` method in your
IDE.

```bash
./mvnw spring-boot:run
```

Once the application is running you can run the help command to see all the commands available.

```bash
help
```

```bash
AVAILABLE COMMANDS

Built-In Commands
       help: Display help about available commands
       stacktrace: Display the full stacktrace of the last error.
       clear: Clear the shell screen.
       quit, exit: Exit the shell.
       history: Display or save the history of previously run commands
       version: Show version info
       script: Read and execute commands from a file.

You Tube Stats Commands
       all: List all videos on the channel.
       report: Run a report based on all the videos for the current year.
       filter-by-year: Filter videos by year.
       recent: List recent videos by max count.

```