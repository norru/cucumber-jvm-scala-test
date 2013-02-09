#!/bin/sh
if [ ! -e CLASSPATH ] ; then
	mvn dependency:build-classpath -Dmdep.outputFile=CLASSPATH || exit 1
fi

CLASSPATH=`cat CLASSPATH`:target/classes:target/test-classes
java -cp $CLASSPATH\
	net.itadinanta.cucumber.Main\
	--glue net.itadinanta.cucumber src/test/resources/\
	--format pretty
