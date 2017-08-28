#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>


int MAXFUEL = 100;
int fuelLevel = 0;

void handler(int sig) {
	#ifdef DEBUG
	printf("PLANE: In Signail Handler \n");
	printf("PLANE: Signal id %d received \n", sig);
	#endif

	if(sig == SIGUSR2) {
		fuelLevel = MAXFUEL;
		#ifdef DEBUG
		printf("PLANE: SIGUSR2 signal received, setting fuel level to %d \n", MAXFUEL);
		#endif
	} else if(sig == SIGALRM) {
		fuelLevel = fuelLevel - 10;
		#ifdef DEBUG
		printf("PLANE: SIGALRM signal received, reducing fuel level \n");
		#endif
	}
	alarm(3);
}

main() {
	signal(SIGUSR2, handler);
	signal(SIGALRM, handler);	

	#ifdef DEBUG
	printf("PLANE: Started %d\n", getpid());
	#endif
	
	kill(getpid(), SIGUSR2);
	alarm(3);	

	while(1) {
		if(fuelLevel < 30 ) {
			kill(getppid(), SIGUSR1);
		} 
		 if(fuelLevel == 0) {
			#ifdef DEBUG
			printf("PLANE: Child %d\n exiting shortly \n", getpid());
			#endif
			sleep(1);
			exit(0);
		}
		sleep(10);
	}
} 
