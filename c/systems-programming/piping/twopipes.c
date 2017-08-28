#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	pid_t pid = getpid();
	printf("STARTING PROCESS %d\n", pid);

	int fileDescriptors[2];
	int fileDescriptors2[2];

	int pipeStatus = pipe(fileDescriptors);
	int pipe2Status = pipe(fileDescriptors2);

	if(pipeStatus == -1 || pipe2Status == -1) {
		perror("Failed to create the pipe");
		return 1;
	}	

	if(fork()) {
		if(fork()) {
			if(!fork()) {
				dup2(fileDescriptors2[0], STDIN_FILENO);
				close(fileDescriptors2[1]);
				close(fileDescriptors[0]);
				close(fileDescriptors[1]);
				execlp("sort", "sort", "-n", "-k", "4", (char *)0);
			}
		} else {
			dup2(fileDescriptors[0], STDIN_FILENO);
			close(fileDescriptors[1]);
			dup2(fileDescriptors2[1], STDOUT_FILENO);
			close(fileDescriptors2[0]);
			execlp("grep", "grep", "root", (char *)0);
		}
	} else {
		dup2(fileDescriptors[1], STDOUT_FILENO);
		close(fileDescriptors[0]);
		close(fileDescriptors2[0]);
		close(fileDescriptors2[1]);
		execlp("ps", "ps", "-ef", (char *)0);
	}
	close(fileDescriptors[0]);
	close(fileDescriptors[1]);
	close(fileDescriptors2[0]);
	close(fileDescriptors2[1]);
	sleep(3);
	printf("PARENT: PROCESS Waiting on children to complete \n");

	while(wait(NULL) > 0);

	printf("Final Print Statement before exit\n");
	exit(0);
}
