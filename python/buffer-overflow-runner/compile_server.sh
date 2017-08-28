#!/bin/sh
gcc -g -fno-stack-protector -z execstack -mpreferred-stack-boundary=2 -o ./output/prog ./src/prog.c
