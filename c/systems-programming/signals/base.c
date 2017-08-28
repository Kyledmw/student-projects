#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

int childPids[5] = {-1, -1, -1, -1, -1};
const int MAX_PLANES = 5;
int planeAmount = 0;

void printChildPids() {
	int p = 0;
	while(p < MAX_PLANES) {
		printf("Index: %d  Value: %d \n",p,  childPids[p]);							
		p++;
	}
}

void handler(int mysignal) {
	if(mysignal == SIGCHLD) {
		printf("Child has died, updating list \n");
		pid_t childHasFinished;
		int returnStatus;
		int indexOfPid;
		if((childHasFinished = wait(&returnStatus)) > 0) {
			int i = 0;
			while(i < MAX_PLANES) {
				if(childPids[i] == childHasFinished) {
					int j = i;
					while(j < MAX_PLANES) {
						if(j + 1 != MAX_PLANES) {
							childPids[j] = childPids[j + 1];
						} else {
							childPids[j] = -1;
						}
						j++;
					}

					planeAmount--;
					printf("List is updated \n");
					
					#ifdef DEBUG
					printChildPids();
					#endif
					break;	
				}
				i++;
			}
			
		}
	} else if(mysignal == SIGUSR1) {
		printf("A planes fuel is low \n");
	}
}

main() {
	signal(SIGCHLD, handler);
	signal(SIGUSR1, handler);

	int quit = 0;

	while(quit != 1) {
		fflush(stdout);
		printf("1.Launch \n2.Status \n3.Refuel \n4.Quit\n");
		char line[256];
		char command[256];
		strcpy(command, "");
		int id = -1;

		fgets(line, sizeof(line), stdin);
		sscanf(line, "%s %d", command, &id);
		if(strcmp(command, "Launch") == 0) {
			if(planeAmount == MAX_PLANES) {
				printf("Error, max amount of planes launched \n");
			} else {
				if(!(childPids[planeAmount] = fork())) {
					execlp("./plane", "plane", (char *)0);
				} 	
				#ifdef DEBUG
				printChildPids();
				#endif
				planeAmount++;
			}
		} else if(strcmp(command, "Status") == 0) {
			printf("Plane Amount: %d \n", planeAmount);
			if(planeAmount) {
				printf("Plane ids \n");
				int i = 0;
				while(i < MAX_PLANES) {
					if(childPids[i] != -1) {
						printf("%d Plane: %d \n", (i + 1), childPids[i]);
					}
					i++;
				}
			} else { 
				printf("There are no planes launched \n");
			}
		} else if(strcmp(command, "Refuel") == 0) {
			int idExists = 0;
			if(id != -1) {
				int i = 0;
				while(i < MAX_PLANES) {
					if(id == childPids[i]) {
						idExists = 1;
						kill(childPids[i], SIGUSR2);
						break;
					}
					i++;
				}
			} else {
				if(!idExists) {
					printf("Error, process %d doesnt exist \n", id);
				}
			}
		} else if(strcmp(command, "Quit") == 0) {
			quit = 1;
		}
		sleep(2);
	}
}
