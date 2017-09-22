#!/bin/sh
cd ../
var=$(pwd)
PRO_NAME="${var##/*/}"
#PRO_NAME=netease
PRO_TYPT=java

PID=$(ps -ef|grep $PRO_NAME.jar |grep $PRO_TYPT |grep -v grep|awk '{print $2}')
kill -9 $PID
echo -e "\e[47;31;5m进程已结束\e[0m"
