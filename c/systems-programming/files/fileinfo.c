#include <stdio.h>
#include <sys/stat.h>

/*
 * Prints file info of the given filename
 */
void printFileInfo(char* filename) {
	printf("%s info: \n", filename);
	
	struct stat fileInfo;
	
	if(stat(filename, &fileInfo) == 0) {
		printf("uid: %ld \n", fileInfo.st_uid);
		printf("gid: %ld \n", fileInfo.st_gid);
		printf("size: %ld \n", fileInfo.st_size);
		printf("Octal %o \n", (fileInfo.st_mode & (S_IRWXU | S_IRWXG | S_IRWXO)));
		printf("No. of blocks: %ld \n", (fileInfo.st_blocks));
		printf("Time the file was last used: %s \n", ctime(&fileInfo.st_atime));
		if(fileInfo.st_mode & S_IFDIR) {
			printf("This is a directory \n");
		} else {
			printf("This is not a directory \n");
		}
	} else {
		perror("stat");
		printf("\n");
	}

}

main() {
    //Testing with different files
	printFileInfo("machine.c");
	printFileInfo("dir");
	printFileInfo("non_existant");
	printFileInfo("no_read_access.txt");
}
