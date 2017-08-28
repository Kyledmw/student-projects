#include <stdio.h>
#include <string.h>

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int send_str_to_socket (int s1, char *str)
{
  char output_str[150];

  strcpy (output_str, str);
  write (s1, output_str, strlen(output_str));
  printf ("The output address is %p\n", &output_str);
  return 0;
}

int  main (int argc, char *argv[])
{
  struct sockaddr_in   server, client;
  socklen_t            len = sizeof (struct sockaddr_in);
  int                  s,socket1, out_socket = 1;
  char                 inputstr[300];

  server.sin_addr.s_addr = INADDR_ANY;
  server.sin_family = AF_INET;
  server.sin_port = htons(4567);

  s = socket (PF_INET, SOCK_STREAM, 0);
  if ((setsockopt (s, SOL_SOCKET, SO_REUSEADDR, &out_socket, sizeof(out_socket))) < 0)
    perror ("setsockopt failed");
  bind (s, (struct sockaddr *) &server, sizeof (server));
  listen (s, 10);

  while (1)
    {
      socket1 = accept (s, (struct sockaddr *)&client, &len);
      printf ("Connection from %s\n", inet_ntoa (client.sin_addr));
      memset (inputstr, 0, 300);
      read (socket1, inputstr, 300);
      send_str_to_socket (socket1, inputstr);
      close (socket1);
    }
  return 0;
}