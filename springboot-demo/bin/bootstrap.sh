#!/usr/bin/env bash

#Author: Leo
#Blog: http://blog.csdn.net/lc0817
#CreateTime: 2016/12/10 15:07
#Description:

source /etc/profile

#================Var Begin====================
serviceName=$2
serviceDir="/home/leo/service"
jarName="$serviceName.jar"
mainClass="com.hmxw.Application"
tag="$serviceName"
libDir="$serviceDir/lib"
logOutputFormat=$(date +%b" "%d" "%H:%M:%S" "`hostname`)
jvmParam="-Xmx2048M -Xms2048M -XX:PermSize=512M -XX:MaxPermSize=512M"
#================Var End====================


#================Function Begin====================
start(){
    cd $serviceDir
    runningJarCount=$(ps -ef | grep java | grep -w $tag | wc -l)
    if [ $runningJarCount -gt 0 ]; then
        echo "$logOutputFormat $tag is running."
    else
        $JAVA_HOME/bin/java $jvmParam -Dir=$tag $mainClass
        echo "$logOutputFormat Ready to start $tag, if u wanna see the bootstrap process of $tag, please tail the console.out."
    fi
}

stop(){
    echo "$logOutputFormat Ready to stop $tag."
    runningJarCount=$(ps -ef | grep java | grep -w $tag | wc -l)
    if [ $runningJarCount -gt 0 ]; then
        ps -ef|grep java|grep -w $tag|grep -v grep|awk '{print $2}' |xargs -n1 kill -9
    fi
    echo "$logOutputFormat $tag was stopped."
}
#================Function End====================


if [ ! -f $serviceDir/$serviceName/$jarName ]; then
    echo "Cannot find $serviceDir/$serviceName/$jarName ."
    exit
fi

for lib in $libDir/*.jar
do
    libs=$lib:$libs
done
CLASSPATH=$libs$serviceDir/$serviceName/$jarName
export CLASSPATH

#==================Entrance Begin===============
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        sleep 5
        start
        ;;
    *)
    echo "Usage: $0 {start|stop|restart} + serviceName"
    exit 2
esac
#==================Entrance End===============