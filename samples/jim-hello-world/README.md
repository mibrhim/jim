## JIM Hello World
This is the simplest form to use the Jim Library with.

## How to use
You can generate the `jim-java-1.0.0-SNAPSHOT.jar` file and add this file to the local maven repo. using the instruction on the **Compile and use** section in [the main README.md file](../../README.md)

Then you can build and run the project using this commands from the jim-hello-world base project folder
```
mvn clean compile assembly:single
java -jar target/jim-example-1.0-SNAPSHOT-jar-with-dependencies.jar
```

