# Buffer Overflow Runner

## About

Python script to perform a buffer overflow exploit on the provided prog.c

### Features

1. Setups machine environment to perform the exploit.
2. Compiles the vulnerable application.
3. Runs the vulnerable application.
4. Generates opcodes from the provided shellcode.
5. Generates the buffer overflow string.
6. Sends the overflow string to the vulnerable program.
7. Prints the results to the console.
8. Closes the vulnerable application.

## Technology

Technology | Description
------------ | -------------
C | Language used for the vulnerable application
Python | Programming language used for the exploit runner.
ASM | Exploit shellcode

## License

Apache Version 2.0 © [Kyle Williamson ](https://github.com/kyledmw)
