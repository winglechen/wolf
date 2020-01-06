#!/bin/bash

APPLICATION_NAME="@project.name@"

cd ../
BASE_PATH=`pwd`
cd ./bin

if [ ! -d ${BASE_PATH}/logs ] ; then
	mkdir -p ${BASE_PATH}/logs
fi

## set java path
JAVA=$(which java)
if [ -z "$JAVA" ]; then
  	echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.8) in your PATH." 2>&2
    exit 1
fi

MAIN_JAR=${BASE_PATH}/lib/${APPLICATION_NAME}.jar
APP_PID=`ps -ef | grep "$MAIN_JAR" | grep -v grep | awk '{print $2}'`