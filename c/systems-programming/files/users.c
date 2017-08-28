#include <stdio.h>
#include <sys/types.h>
#include <pwd.h>

main() {
	struct passwd* passwdForUser = getpwuid(getuid());
	
	struct passwd* passwd = getpwent();
	if(passwdForUser) {
		printf("Users in the same group: \n");
		while(passwd) {
			if(passwd->pw_gid == passwdForUser->pw_gid) {
				printf("Name: %s \n", passwd->pw_name);
			}
			passwd = getpwent();
		}
	} else {
		perror("getpwuid");
	}
}
