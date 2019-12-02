#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include <signal.h>

int isRunnging = 1;
int isPaused = 0;

void sigHandler(int sigNum)
{
	if(sigNum == SIGINT)
		isRunnging = 0;
	else
		if(sigNum == SIGTSTP)
			isPaused == !isPaused;
		else
			printf("Something weird happened");
}

int main( int argc, char* argv[] )
{
	if (signal(SIGINT, sigHandler) == SIG_ERR)
  		printf("\ncan't catch SIGINT\n");
	if (signal(SIGTSTP, sigHandler) == SIG_ERR)
  		printf("\ncan't catch SIGINT\n");
	if (argc != 3)
	{
		printf("Wrong args count");
		exit(1);
	}

	int port = atoi(argv[2]);
	char* host = argv[1];
	// int port = 12121;
	// char* host = "127.0.0.1";
	int outputSocketDescriptor = 0;
	struct sockaddr_in serverAddress;
	struct hostent *server;
	char someChar = 0;

	if ((outputSocketDescriptor = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("Failed to create socket");
		exit(1);
	}

	if( (server = gethostbyname( host )) == NULL)
	{
		printf("Failed to gethostbyname()");
		exit(1);
	}

	bzero((char *) &serverAddress, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(port);
	bcopy( (char *) server->h_addr, (char *) &serverAddress.sin_addr.s_addr, server->h_length );

	if( connect( outputSocketDescriptor, (struct sockaddr *) &serverAddress, sizeof( serverAddress ) ) < 0 ) 
	{
		printf("Failed to connect to the server");
		exit(1);
	}

	while (isRunnging)
	{
		someChar = getchar();
		write( outputSocketDescriptor, &someChar, sizeof( char ) );
	}

	printf("\nexiting\n");
	close(outputSocketDescriptor);
	return 0;
}