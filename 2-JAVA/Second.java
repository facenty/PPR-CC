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
        System.out.println(outHost);
        System.out.println(outPort);
        System.out.println(inPort);

        System.out.println("====================");
        System.out.println("    Aplikacja #2");
        System.out.println("====================");

        try
        {
            //create soap access
            URL url = new URL("http://" + outHost + ":" + outPort + "/?wsdl");
            QName qname = new QName("http://third/", "ThirdService");
            service = Service.create(url, qname);
            third = service.getPort(Third.class);

            //create tcp server
            serverSocket = new ServerSocket(inPort);
        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void run()
    {
        while(true)
        {   
            try
            {
                Socket connection = serverSocket.accept();
                InputStream inputStream = connection.getInputStream();
                int count = 0;
                byte[] buffer = new byte[4096];
                System.out.println("Connected: "+ connection.isConnected());
                while(connection.isConnected() && (count = inputStream.read(buffer)) > 0)
                {
                    third.printMessage(convertToHexString(buffer, count));
                }
                System.out.println("Disconnected: "+ connection.isConnected());
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Second second = new Second(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        second.run();
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    
    private String convertToHexString(byte[] txt, int length)
    {
        StringBuilder sb = new StringBuilder();
        char[] hexChars = new char[2];
        for(int i = 0; i < length; ++i)
        {
            int v = txt[i] & 0xFF;
            hexChars[0] = HEX_ARRAY[v >>> 4];
            hexChars[1] = HEX_ARRAY[v & 0x0F];
            String myString = new String(hexChars);
            sb.append(myString);
        }
        return sb.toString();
    }

}