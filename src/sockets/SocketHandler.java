package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketHandler extends Thread {
    Socket socket;
    public SocketHandler(Socket serverSocket) {
        this.socket = serverSocket;
    }

    public void run() {
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //open an output stream to the socket writing
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //open an input stream to the socket for reading
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read a line of the received message
        String message = null;
        try {
            message = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer result = Server.calculate(message);
        //print out the received line into the console
        System.out.println("<<Server>> : message received: " + message);

        //write a response to the socket
        out.println(result);
        //force the written buffer to be sent
        out.flush();
        System.out.println("<<Server>>: response sent: " + result);

        //close the socket
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}