package sockets;
import java.io.*;
import java.net.*;

public class Server {
    public static final int PORT = 3333;
    public static final String response = "Greetings from Server.";

    public static void main(String[] args) {
        try {
            //create a server socket to listen on given port
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                //accept a connection from a client
                final Socket socket = serverSocket.accept();
                SocketHandler obj = new SocketHandler(socket);
                obj.start();
            }

//            //open an output stream to the socket writing
//            PrintWriter out = new PrintWriter(socket.getOutputStream());
//            //open an input stream to the socket for reading
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            //read a line of the received message
//            String message = in.readLine();
//            Integer result = calculate(message);
//            //print out the received line into the console
//            System.out.println("<<Server>> : message received: " + message);
//
//            //write a response to the socket
//            out.println(result);
//            //force the written buffer to be sent
//            out.flush();
//            System.out.println("<<Server>>: response sent: " + result);
//
//            //close the socket
//            socket.close();
        }
        //handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer calculate(String message) {
        var args = message.split("\\+");
        return Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
    }
}