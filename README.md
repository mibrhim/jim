# JIM (Java Internal Messaging)
Java Internal Messaging (JIM) makes it easy to send internal messages between different modules entry points.

## What is JIM
It is a platform built to make it easy to anyone to decouple the logic in different modules where each module run in a different thread, these modules can communicate with each other using the `JimPlatform`.
Each module could have his own id which it will receive and send the messages with it, the internal messages between the two modules also have their own id so that each message could represent a certain logic.

## Why JIM
Sometimes we need to implement a monoloth appliction where all the code will run in the same process such as desktop applications or server side service,
however we want also to decouple the different modules that this application or service consists of and make them concurrent.

## How to use JIM
The simplest way to use that to create an entry point class in your module and make it extends `JimAgent` such as:
```java
public class ModuleOneEntry extends JimAgent{
...
@Override
public void onReceive(IMessage im){
  // TODO: do anything with the received internal message here
}
```
Then you can register this module to the `JimPlatform` such as:
```java
final static int MODULE_ONE = 1;
try{
  JimPlatform.register(MODULE_ONE, ModuleOneEntry.class);
}catch(Exception e){
...
}
```
Now, you can create internal message `IMessage` using `IMessageBuilder` and send this internal messages to any module, such as:
```java
final static int MODULE_ONE = 1;
final static int MODULE_TWO = 2;

final static int NEW_INTERNAL_MESSAGE = 1;

IMessage im = new IMessageBuilder().receiver(MODULE_TWO).sender(MODULE_ONE).messageType(NEW_INTERNAL_MESSAGE).build();
JimPlatform.send(im);
```
So this message will be received in the module entry point class in `public onReceive(IMessage im)` function.

For More details you can visit the **Samples Folder** which contains more samples that could be tried.
[Check Samples](samples/)

## Compile and Use
To use this library you should compile it using maven and java 1.8+ then you can use the generated `.jar`, you can do this using the following commands.
```
git clone https://github.com/mhmod1990/jim.git
cd jim
mvn package
```
then you will find the generated `.jar` file in the target folder where you can use it directly or if you want to use it with maven local repository, you can execute this command
```
 mvn install:install-file \                  
   -Dfile=target/jim-java-1.0.0-SNAPSHOT.jar \
   -DgroupId=org.mhmod.jim \
   -DartifactId=jim-java \
   -Dversion=1.0.0-SNAPSHOT \
   -Dpackaging=jar \
   -DgeneratePom=true
```
then you will be able to add this library as a dependency for your project `pom.xml` file such as:
```xml
<dependencies>
        <dependency>
            <groupId>org.mhmod.jim</groupId>
            <artifactId>jim-java</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
</dependencies>
```

## Finaly
Kindly contribute to this library and give star if you find it useful, Thanks :).
