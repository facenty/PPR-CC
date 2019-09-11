#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 

int main( int argc, char* argv[] )
{
	// if (argc != 3)
	// {
	// 	printf("Wrong arg count");
	// 	exit(1);
	// }

	// int port = atoi(argv[2]);
	// char* host = argv[1];
	int port = 12121;
	char* host = "127.0.0.1";
	int outputSocketDescriptor = 0;
	struct sockaddr_in serverAddress;
	struct hostent *server;
	char someChar = 0;

	if (outputSocketDescriptor = socket(AF_INET, SOCK_STREAM, 0) < 0)
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

	while (1)
	{
		someChar = getchar();
		write( outputSocketDescriptor, &someChar, sizeof( char ) );
	}

	close(outputSocketDescriptor);
	return 0;
}