#!/bin/bash

CURRENT_PATH=$(cd `dirname $0`; pwd)
cd ${CURRENT_PATH}
. ${CURRENT_PATH}/common.sh

if [[ ${APP_PID} -gt 0 ]]; then
    echo "应用已启动，请先执行stop.sh或者选用restart.sh"
    stopTimeout=1
    return 1
fi

echo "start execute ..."

JAVA_OPTS="-server -Xms256m -Xmx256m -Xss256k -XX:SurvivorRatio=2 -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs"
JAVA_OPTS=" $JAVA_OPTS -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"
JAVA_OPTS=" $JAVA_OPTS -DappName=$APPLICATION_NAME"

JAVA_OPTS=" $JAVA_OPTS -Dspring.profiles.active=@profileActive@"
#JAVA_OPTS=" $JAVA_OPTS -Dspring.config.location=/home/app/union-deploy-dubbo/config/application.yml"


LOG_PATH="/data/logs/$APPLICATION_NAME"

if [ ! -d $LOG_PATH ] ; then
	mkdir -p $LOG_PATH
fi

RUN_COMMAND_LINE="$JAVA $JAVA_OPTS -jar $MAIN_JAR 1>>$LOG_PATH/root.log 2>&1 &"
echo ${RUN_COMMAND_LINE}
echo ${RUN_COMMAND_LINE}|sh

stopTimeout=0
