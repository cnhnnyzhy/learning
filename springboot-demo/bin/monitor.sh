#!/bin/sh
cd ../
var=$(pwd)
PRO_NAME="${var##/*/}"
#PRO_NAME=netease
PRO_TYPT=java
PID=$(ps -ef|grep $PRO_NAME.jar |grep $PRO_TYPT |grep -v grep|awk '{print $2}')
if [ "$PID" != "" ];then
   echo "------------------------------------------"
   echo -e "\e[47;31;5mThe $PRO_NAME status is running...\e[0m"
   echo "------------------------------------------"
else
   echo "------------------------------------------"
   echo -e "\e[47;31;5mThe $PRO_NAME is stop....\e[0m"
   echo "------------------------------------------"
fi
