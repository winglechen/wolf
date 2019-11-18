#!/bin/bash

BIN_PATH=`dirname $0`
cd ${BIN_PATH}
. common.sh

if [[ ${APP_PID} -gt 0 ]]; then
    kill ${APP_PID}
    echo "Stop successful"
    exit 0
else
    echo "应用未启动，不需要执行stop"
    exit 0
fi