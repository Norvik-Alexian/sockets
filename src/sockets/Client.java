package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3333;

    public static void send(String message) throws IOException {
        try {
            //open a socket
            Socket socket = new Socket(HOST, PORT);

            //open an output stream to the socket writing
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //open an input stream to the socket for reading
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //write a message to the socket
            out.println(message);
            //force the written buffer to be sent
            out.flush();

            System.out.println("<<Client>>: message sent: " + message);

            //read a line of the response
//            String response = in.readLine();
            //print out the response to the console
//            System.out.println("<<Client>>: response received: " + response);

        }
        //handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
//            send("Hello from Client");
            send("2+3");
            for (int i = 0; i < 1000; i++) {
                send("1+"+i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}