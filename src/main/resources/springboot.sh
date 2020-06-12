#!/bin/bash

export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_191
export JRE_HOME=$JAVA_HOME/jre

cd `dirname $0`

API_NAME=info-tag-service-sk-0.0.1-SNAPSHOT
JAR_NAME=$API_NAME\.jar
PID=$API_NAME\.pid

usage() {
    echo "Usage: sh [start|stop|restart|status]"
    exit 1
}

is_exist(){
  pid=`ps -ef|grep $JAR_NAME|grep -v grep|awk '{print $2}' `
  # 1:exist, 0:not exist
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}


start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup $JRE_HOME/bin/java -Xms256m -Xmx512m -jar $JAR_NAME >/dev/null 2>&1 &
    echo $! > $PID
    echo ">>> [SUCCESS] start $JAR_NAME successed PID=$! <<<"
   fi
  }

stop(){
  #is_exist
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9  $pid
    sleep 2
    echo ">>> [SUCCESS] $JAR_NAME process stopped <<<"
  else
    echo ">>> [SUCCESS] ${JAR_NAME} is not running <<<"
  fi
}

status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

restart(){
  stop
  start
}

case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
exit 0
