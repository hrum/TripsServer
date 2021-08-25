package com.shuvzero.tripsserver;

import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args) {
        int portNumber = 6667;
        System.out.println("Starting server on port: " + portNumber);
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Client is connected");
            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);
                String serverMessage = inputLine + ", lol";
                System.out.println("Sending to client: " + serverMessage);
                toClient.println(serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

}