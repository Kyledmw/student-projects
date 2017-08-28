#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <pwd.h>

/*
 * Read the data from the given status file
 */
void readStatusFile(FILE* stat) {
	char* line = NULL;
	size_t len = 0;
	ssize_t read;

	while((read = getline(&line, &len, stat)) != -1) {
		char* token = strtok(line, "\t");
		if(strcmp("Name:", token) == 0) {
			printf("Process Name: ");
			token = strtok(NULL, "\t");
			printf("%s \n", token);
		} else if(strcmp("State:", token) == 0) {
			printf("Process State: ");
			token = strtok(NULL, "\t");
			printf("%s \n", token);
		} else if(strcmp("Uid:", token) == 0) {
			printf("Username: ");
			token = strtok(NULL, "\t");
			char uid[] = "";
			while(token) {
				strcat(uid, token);
				token = strtok(NULL, "\t");
			}
			struct passwd* pwd = getpwuid(atoi(uid));
            if(pwd != NULL) {
			     printf("%s \n\n", pwd->pw_name);
            } else {
		          perror("getpwuid");
            }
		} else if(strcmp("VmPeak:", token) == 0) {
			printf("Maximum Virtual Memory Usage: ");
			token = strtok(NULL, "\t");
			printf("%s \n", token);
			break;
		} 
	}	
}

/*
 * Read the data from the given io file
 */
void readIOFile(FILE* io) {
	char* line = NULL;
	size_t len = 0;
	ssize_t read;

	while((read = getline(&line, &len, io)) != -1) {
		char* token = strtok(line, " ");
		if(strcmp("write_bytes:", token) == 0) {
			printf("Number of bytes written to the storage layer: ");
			token = strtok(NULL, " ");
			printf("%s \n", token);
		}
	}
}

/*
 * Read the data from the given cmdline file
 */
void readCmdlineFile(FILE* cmd) {
	char buff[255];
	fgets(buff, 255, (FILE*) cmd);
	printf("Full Command and Path that started the Process: %s \n\n", buff);
}

/*
 * Read the data from the given wchan file
 */
void readWchanFile(FILE* wchan) {
	char buff[255];
	fgets(buff, 255, (FILE*) wchan);
	printf("Waiting Channel: %s \n", buff);
}

int main(int argc, char* argv[]) {
	if(argc != 2) {
		printf("One argument expected.\n"); 
		return 0;
	} 

	//Set up path to folder for the given procedure
	char dir[11];
	strcpy(dir, "/proc/");
	strcat(dir, argv[1]);

	//Set up path for status file
	char statDir[18];
	strcpy(statDir, dir);
	strcat(statDir, "/status");
	FILE* stat = fopen(statDir, "r");
	
	if(stat == NULL) {
		printf("Invalid dir %s \n", statDir);
		return 1;
	}
	
	readStatusFile(stat);
	
	fclose(stat);

	//Set up path for io file
	char ioDir[14];
	strcpy(ioDir, dir);
	strcat(ioDir, "/io");
	FILE* io = fopen(ioDir, "r");
	
	if(io == NULL) {
		printf("Invalid dir %s \n", ioDir);
		return 1;
	}
	
	readIOFile(io);	

	fclose(io);
	
	//Set up path for cmdline file
	char cmdlineDir[19];
	strcpy(cmdlineDir, dir);
	strcat(cmdlineDir, "/cmdline");
	FILE* cmd = fopen(cmdlineDir, "r");

	if(cmd == NULL) {
		printf("Invalid dir %s \n", cmdlineDir);
		return 1;
	}

	readCmdlineFile(cmd);

	fclose(cmd);
	
	//Set up path for wchan file
	char wchanDir[17];
	strcpy(wchanDir, dir);
	strcat(wchanDir, "/wchan");
	FILE* wchan = fopen(wchanDir, "r");

	if(wchan == NULL) {
		printf("Invalid dir %s \n", wchanDir);
		return 1;
	}

	readWchanFile(wchan);	

	fclose(wchan);
}
