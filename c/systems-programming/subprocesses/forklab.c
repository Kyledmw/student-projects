#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
	printf("STARTING: Process is %d MY PARENT is%d\n", getpid(), getppid());
	int x = 1;
	int rValue = fork();

	if(rValue != 0) {
		printf("PARENT: PROCESS PID is %d PARENT PID is%d\n", getpid(), getppid());
		printf("RVALUE is %d\n", rValue);
		printf("PARENT: x is %d\n", x);
		printf("PARENT: PROCESS PID is %d Goodbye\n", getpid());
		exit(0);
	} else {
		printf("CHILD: PROCESS PID is %d PARENT PID is %d\n", getpid(), getppid());
		printf("RVALUE is %d\n", rValue);
		x = 2;
		printf("CHILD: x is %d\n", x);
		printf("CHILD: PROCESS PID is %d Goodbye\n", getpid());
		exit(0);
	}
}
