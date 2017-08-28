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
		if(!fork()) {
			printf("CHILD2: Process is %d\n", getpid());
			printf("File Descriptor at 0: %d\n", fileDescriptors[0]);
			printf("File Descriptor at 1: %d\n", fileDescriptors[1]);
			
			char messageRead[256];
			int numBytesRead = read(fileDescriptors[0], messageRead, sizeof(messageRead));
			printf("Number of bytes read %d\n", numBytesRead);
			printf("Message read: %s\n", messageRead);


			char message[256];
			sprintf(message, "hello from process id %d", getpid());
			int written = write(fileDescriptors2[1], message, strlen(message) + 1);
			printf("Number of bytes written: %d\n", written);

			exit(0);
		}
	} else {
		printf("CHILD1: Process is %d\n", getpid());
		printf("File Descriptor at 0: %d\n", fileDescriptors[0]);
		printf("File Descriptor at 1: %d\n", fileDescriptors[1]);

		char message[256];
		sprintf(message, "hello from process id %d", getpid());
		int written = write(fileDescriptors[1], message, strlen(message) + 1);
		printf("Number of bytes written: %d\n", written);
			
		char messageRead[256];
		int numBytesRead = read(fileDescriptors2[0], messageRead, sizeof(messageRead));
		printf("Number of bytes read %d\n", numBytesRead);
		printf("Message read: %s\n", messageRead);

		exit(0);
	}
	sleep(3);
	printf("PARENT: PROCESS Waiting on children to complete \n");

	while(wait(NULL) > 0);

	printf("Final Print Statement before exit\n");
	exit(0);
}
