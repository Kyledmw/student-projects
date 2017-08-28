#!/bin/sh
nasm -f elf32 ./src/shellcode.asm -o ./shellcode.o
objdump -d ./shellcode.o | grep '[0-9a-f]:' | grep -v 'file' | cut -f2 -d: | cut -f1-6 -d' ' | tr -s ' ' | tr '\t' ' ' | sed 's/ $//g' | sed 's/ /\\x/g' | paste -d '' -s | sed 's/^/"/' | sed 's/$/"/g'
rm ./shellcode.o
