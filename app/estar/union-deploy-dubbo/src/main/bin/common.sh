#!/bin/bash
APPLICATION_NAME="@project.name@"

CURRENT_PATH=$(cd `dirname $0`; pwd)
PROJECT_PATH=$(cd `dirname $0`; cd ../ ; pwd)
BIN_PATH="${PROJECT_PATH}/bin"


#if [ ! -d ${BASE_PATH}/logs ] ; then
#	mkdir -p ${BASE_PATH}/logs
#fi

## set java path
#JAVA=$(which java)
#if [ -z "$JAVA" ]; then
#  	echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.8) in your PATH." 2>&2
#    exit 1
#fi

JAVA="${JAVA_HOME}/bin/java"

MAIN_JAR=${PROJECT_PATH}/lib/${APPLICATION_NAME}.jar
APP_PID=`ps -ef | grep "$MAIN_JAR" | grep -v grep | awk '{print $2}'`

echo "pid: ${APP_PID}"