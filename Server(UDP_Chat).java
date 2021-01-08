import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] arg) throws IOException {
        try {
            DatagramSocket serverSocket = new DatagramSocket(4000);
            System.out.println("Server is up");

            byte[] requestBytes =new byte[4096];
            byte[] responseBytes;

            Scanner sc = new Scanner(System.in);
            //create a client packet for receiving the client request
            while(true) {
                System.out.println("Server is ready to communicate");
                DatagramPacket clientPacket = new DatagramPacket(requestBytes, requestBytes.length);

                //Receive Client's request into the client packet
                serverSocket.receive(clientPacket);
                String req = new String(clientPacket.getData());
                System.out.println("Client" +clientPacket.getSocketAddress()+ ": "+ req);

                //take input from console + convert to bytes then send
                String input = sc.nextLine();
                responseBytes = input.getBytes();

                //extract ip Address and port number of the client
                InetAddress clientIP = clientPacket.getAddress();
                int clientPort = clientPacket.getPort();

                DatagramPacket myServerPacket = new DatagramPacket(responseBytes, responseBytes.length, clientIP, clientPort);
                serverSocket.send(myServerPacket);
                //send server's response to client

                requestBytes = new byte[4096];
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
