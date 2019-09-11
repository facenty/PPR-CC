package third;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Third {

    // Adres do publikacji pliku wsdl
    public static final int port = 12345;
    public static final String url = "http://localhost:" + port + "/";

    public Third() {}

    @WebMethod(operationName = "printMessage")
    public void printMessage(String msg) {
        System.out.print(msg);
    }

    public static void main(String[] args) {
        System.out.println("====================");
        System.out.println("    Aplikacja #3");
        System.out.println("====================");
        
        Third third = new Third();
        Endpoint.publish(third.url, third);
    }
}