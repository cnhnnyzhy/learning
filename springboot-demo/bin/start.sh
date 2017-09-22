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
	cd /apps/www/task/$PRO_NAME
	nohup java -jar $PRO_NAME.jar > /dev/null 2>&1 &
	echo "------------------------------------------"
	echo -e "\e[47;31;5mThe $PRO_NAME starting ...\e[0m"
	echo "------------------------------------------"
fi