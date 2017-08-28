#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {

	if(fork()) {
		printf("PARENT: Process Id is %d, Parent Id is %d\n", getpid(), getppid());
		pid_t childHasFinished;
		int returnStatus;
		while((childHasFinished = wait(&returnStatus)) > 0) {
			printf("PARENT: Child Process %d has finsihed\n", childHasFinished);
			printf("PARENT: Child exited with code %d\n", WEXITSTATUS(returnStatus));
		} 
	} else {
		printf("CHILD: Proces Id is %d, Parent Id is %d\n", getpid(), getppid());
		execlp("./twochildren", "./twochildren", (char *)0);
	}

}
