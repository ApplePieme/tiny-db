#!/bin/bash

scriptpath=`realpath -e $0` 
echo "ScriptPath=${scriptpath}"

workspace=`dirname ${scriptpath}`
echo "WorkDir=${workspace}"

cd $workspace

jarname=tiny-db.jar
jarfile=${workspace}/${jarname}

ps aux | grep ${workspace} | grep ${jarfile} | grep -v "grep" | awk '{print $2}' | while read pid
do
    kill ${pid}
    echo "Killed service with pid ${pid}."
done
