#include <stdio.h>
#include <sys/stat.h>
#include <dirent.h>
#include <errno.h>
#include <sys/types.h>
#include <pwd.h>

/**
 * Masks the last few bits we dont care about for display purposes
 */
int maskOctalPermissions(int octalPermission) {
	return (octalPermission & (S_IRWXU | S_IRWXG | S_IRWXO));
}

int main() {
	struct dirent* direntp;
	DIR* dirp;
	
	if((dirp = opendir("/")) == NULL) {
		perror("Failed to open directory");
		return 1;
	}

	while((direntp = readdir(dirp)) != NULL) {
		struct stat fileInfo;
		if(stat(direntp->d_name, &fileInfo)) {
			if(fileInfo.st_mode & S_IFDIR || direntp->d_name != "." || direntp->d_name != ".." ) {				
                struct passwd* passwdForId = getpwuid(fileInfo.st_uid);
				if(passwdForId != NULL) {
					printf("%s\t%s\t%o\n",passwdForId->pw_name, direntp->d_name, maskOctalPermissions(fileInfo.st_mode));
				} else {
					perror("getpwuid");
				}	
			}
		} else {
			perror("stat");
		}
	}


	while((closedir(dirp) == -1) && (errno == EINTR));
	return 0;
}
