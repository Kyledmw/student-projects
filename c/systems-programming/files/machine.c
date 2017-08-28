#include <stdio.h>
#include <sys/utsname.h>

main() {
	struct utsname uts;

	if(uname(&uts) < 0) {
		perror("uname");
	} else {
		printf("System Name: %s \n", uts.sysname);
		printf("Nodename: %s \n", uts.nodename);
		printf("Release: %s \n", uts.release);
		printf("Version: %s \n", uts.version);
		printf("Machine: %s \n", uts.machine);
	}

}
