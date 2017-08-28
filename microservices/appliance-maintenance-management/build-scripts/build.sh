#!/bin/bash
rm -r ../build
mkdir ../build
mvn package -Dmaven.test.skip=true
