package second;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import third.Third;

import java.io.*;
import java.net.*;

public class Second {

    //SOAP Objects
    private Service service;
    private Third third;

    // TCP objects
    private ServerSocket serverSocket;

    public Second(String outHost, int outPort, int inPort)
    {
        // System.out.println(outHost);
        // System.out.println(outPort);
        // System.out.println(inPort);

        System.out.println("====================");
        System.out.println("    Aplikacja #2");
        System.out.println("====================");

        try
        {
            //create soap access
            URL url = new URL("http://" + outHost + ":" + outPort + "/?wsdl");
            QName qname = new QName("http://third/", "ThirdService");
            service = Service.create(url, qname);
            Third third = service.getPort(Third.class);

            //create tcp server
            ServerSocket serverSocket = new ServerSocket(inPort);
        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void run()
    {
        while(true)
        {
            Socket connection = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
    }

    public static void main(String[] args) {
        Second second = new Second(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }

    private String hexFromChar(char someChar)
    {
        return String.format("%04x", (int) someChar);
    }

}