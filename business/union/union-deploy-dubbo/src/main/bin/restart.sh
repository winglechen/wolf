#!/bin/bash
CURRENT_DIR=`pwd`

BIN_PATH=`dirname $0`
cd ${BIN_PATH}
. ${CURRENT_DIR}/common.sh

./stop.sh \
&& sleep 2s \
&& ./start.sh