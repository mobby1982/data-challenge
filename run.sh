#!/bin/bash

if type -p java; then
  echo found java executable in PATH
  _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
  echo found java executable in JAVA_HOME     
  _java="$JAVA_HOME/bin/java"
else
  echo "no java"
fi

flag=false

if [[ "$_java" ]]; then
  version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
  echo version "$version"
  if [[ "$version" < "1.8" ]]; then
    echo version is less than 1.8, JVM version fine
    flag=true
  elif [[ "$version" < "1.6" ]]; then
    echo JVM 1.6 / JVM 1.7 is required to run this application !!!!!
  else         
    echo JVM 1.6 / JVM 1.7 is required to run this application !!!!!
  fi
fi
RUN_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:MaxPermSize=256M"
if [ "$flag" = true ]; then
  sbt clean compile test
  sbt run 
fi
