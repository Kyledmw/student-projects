#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	pid_t pid = getpid();
	printf("STARTING PROCESS %d\n", pid);

	int fileDescriptors[2];

	int pipeStatus = pipe(fileDescriptors);

	if(pipeStatus == -1) {
		perror("Failed to create the pipe");
		return 1;
	}	
	
	if(fork()) {
		if(!fork()) {
			printf("CHILD2: Process is %d\n", getpid());
			dup2(fileDescriptors[0], STDIN_FILENO);
			close(fileDescriptors[1]);
			execlp("grep", "grep", "init", (char *)0);
		}
	} else {
		printf("CHILD1: Process is %d\n", getpid());
		dup2(fileDescriptors[1], STDOUT_FILENO);
		close(fileDescriptors[0]);
		execlp("ps", "ps", "-ef", (char *)0);
	}
	close(fileDescriptors[0]);
	close(fileDescriptors[1]);
	sleep(3);
	printf("PARENT: PROCESS Waiting on children to complete \n");

	while(wait(NULL) > 0);

	printf("Final Print Statement before exit\n");
	exit(0);
}
