#!/bin/bash

WAR=dcs-1.0.0.war
if [ ! -e $WAR ]; then
    JAR=target/$WAR
    if [ -e application.yaml ]; then
        cp application.yaml ./target/
    fi
fi
java $CMDVAR -jar ./$WAR