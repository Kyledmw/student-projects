#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
	printf("STARTING: Process is %d MY PARENT is%d\n", getpid(), getppid());

	int rVal1 = 0; 
	int rVal2 = 0;
	if(rVal1 = fork()) {
		if(rVal2 = fork()) {
			printf("PARENT: Process PID is %d PARENT PID is %d\n", getpid(), getppid());
			printf("PARENT: RVALUE1 is %d\n", rVal1);
			printf("PARENT: RVALUE2 is %d\n", rVal2);
			pid_t childHasFinished;
			int returnStatus;
			while((childHasFinished = wait(&returnStatus)) > 0) {
				printf("PARENT: Child Process %d has finsihed\n", childHasFinished);
				printf("PARENT: Child exited with code %d\n", WEXITSTATUS(returnStatus));
			} 
			printf("PARENT: Process PID is %d Goodbye\n", getpid());
			exit(0);
		} else {
			printf("CHILD2: Process PID is %d PARENT PID is %d\n", getpid(), getppid());
			printf("CHILD2: RVALUE1 is %d\n", rVal1);
			printf("CHILD2: RVALUE2 is %d\n", rVal2);
			printf("CHILD2: Process PID is %d Goodbye\n", getpid());
			exit(0);
		}
	} else {
		printf("CHILD1: Process PID is %d PARENT PID is %d\n", getpid(), getppid());
		printf("CHILD1: RVALUE1 is %d\n", rVal1);
		printf("CHILD1: RVALUE2 is %d\n", rVal2);			
		printf("CHILD1: Process PID is %d Goodbye\n", getpid());
		exit(0);
	}
}
