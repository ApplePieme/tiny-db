#!/bin/bash

scriptpath=`realpath -e $0` 
echo "script path: ${scriptpath}"

workspace=`dirname ${scriptpath}`
echo "work dir: ${workspace}"

cd $workspace || exit

jarname=tiny-db.jar
jarfile=${workspace}/${jarname}


mkdir -p log

pid=`ps aux | grep ${jarfile} | grep -v "grep" | awk '{print $2}'`
if [ "X"$pid = "X" ]
then
    java -server -Xms256m -Xmx256m -XX:+UseG1GC -jar ${jarfile}
fi