#!/bin/bash

#export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_191
#export JRE_HOME=$JAVA_HOME/jre

cd `dirname $0`

APP_NAME=%appName%
JAR_NAME=$APP_NAME\.jar

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
    echo ">>> [SUCCESS] ${JAR_NAME} is already running pid=${pid} <<<"
  else
    nohup java -jar $JAR_NAME >/dev/null 2>&1 &
    is_exist
    if [ $? -eq "0" ]; then
      echo ">>> [SUCCESS] start $JAR_NAME pid={${pid}} <<<"
    else
      echo ">>> [FAIL] start $JAR_NAME <<<"
    fi
  fi
}

stop(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">> begin kill -9 $pid <<"
    kill -9 $pid
    is_exist
    if [ $? -eq "0" ]; then
      echo ">>> [FAIL] $JAR_NAME process stopped <<<"
    else
      echo ">>> [SUCCESS] stop $JAR_NAME <<<"
    fi
  else
    echo ">>> [SUCCESS] ${JAR_NAME} is not running <<<"
  fi
}

status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> [SUCCESS] ${JAR_NAME} is running pid={${pid}} <<<"
  else
    echo ">>> [SUCCESS] ${JAR_NAME} is not running <<<"
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
