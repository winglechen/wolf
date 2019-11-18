#!/bin/bash

BIN_PATH=`dirname $0`
cd ${BIN_PATH}
. common.sh

./stop.sh \
&& sleep 2s \
&& ./start.sh