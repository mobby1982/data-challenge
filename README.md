# data-challenge

## JVM Requirements

Java 1.6 - Java 1.7 (required) **Do not use Java 1.8**

## Run.sh 

**Give proper permissions to run.sh. (u+x) **  

**Once run.sh starts executing it will:**

1. Download dependencies **( this will download SBT, Scala Compiler and all the other dependencies, it can take 10-15 minutes depending on the internet speed )**
2. **clean, compile** the project, **run** the tests and then,
3. **run the main challenge** which will generate **solution files** in the tweet_output folder

**If the project is re-run it will be much faster, since it no longer needs to download the dependencies.**