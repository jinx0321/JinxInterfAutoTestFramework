#!/bin/bash
PWD="$( cd "$( dirname "$0"  )" && pwd  )"
echo $PWD
jar_name=`find $PWD -maxdepth 1 -name "*.jar"`
echo $jar_name
/home/jre1.8/jdk1.8/bin/java -Xms100m -Xmx300m -jar $jar_name jfile=$PWD/conf/application.properties> $PWD/logs/stdout.log &
sleep 2
tail -100f $PWD/logs/stdout.log
