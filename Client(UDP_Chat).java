import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] arg) throws SocketException {
        try {
            DatagramSocket clientSocket= new DatagramSocket();
            System.out.println("Client Active");

            InetAddress serverIP=InetAddress.getByName("localhost");
            int serverPort=4000;
            byte[] requestBytes;
            byte[] responseBytes=new byte[4096];

            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.println("You are ready to communicate");

                String input = sc.nextLine();
                if(input.toLowerCase().equals("close")){
                    System.out.println("Connection is closed");
                    clientSocket.close();
                    break;
                }

                requestBytes = input.getBytes();

                DatagramPacket myClientPacket = new DatagramPacket(requestBytes, requestBytes.length, serverIP, serverPort);

                clientSocket.send(myClientPacket);

                DatagramPacket serverPacket = new DatagramPacket(responseBytes, responseBytes.length);

                clientSocket.receive(serverPacket);
                String response = new String(serverPacket.getData());
                System.out.println("Server: " + response);
                responseBytes = new byte[4096];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}