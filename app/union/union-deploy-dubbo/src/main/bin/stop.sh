#!/bin/bash
echo "stop execute ..."

CURRENT_PATH=$(cd `dirname $0`; pwd)
cd ${CURRENT_PATH}
. ${CURRENT_PATH}/common.sh

if [[ ${APP_PID} -gt 0 ]]; then
    kill ${APP_PID}
    echo "Stoping ..."
    return 0
else
    echo "应用未启动，不需要执行stop"
    return 0
fi