package cbb;

import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(15033)) {
            System.out.println("Server started, waiting for client...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream din = new DataInputStream(socket.getInputStream());
                     DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                     BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

                    System.out.println("Client connected");

                    String clientMessage = "";
                    String serverMessage = "";

                    while (!clientMessage.equals("stop")) {
                        clientMessage = din.readUTF();
                        System.out.println("Client says: " + clientMessage);
                        serverMessage = br.readLine();
                        dout.writeUTF(serverMessage);
                        dout.flush();
                    }

                } catch (IOException e) {
                    System.err.println("IOException in client communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}
