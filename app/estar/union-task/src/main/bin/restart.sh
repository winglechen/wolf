#!/bin/bash
echo "restart execute ..."

CURRENT_DIR=$(cd `dirname $0`; pwd)

cd ${CURRENT_DIR}
. ${CURRENT_DIR}/common.sh

. ${CURRENT_DIR}/stop.sh

stopTimeout=0
for i in {1..10}
do
  sleep 2s
  . ${CURRENT_DIR}/start.sh

  if [ $stopTimeout == 0 ]
  then
      break
  fi

  echo "retrying ... "
done

