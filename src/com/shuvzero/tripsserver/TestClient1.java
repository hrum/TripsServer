package com.shuvzero.tripsserver;

import java.io.*;
import java.net.*;

import static com.shuvzero.tripsserver.Server.PORT_NUMBER;

public class TestClient1 {

    public static void main(String[] args) {

        String hostName = "localhost";

        try (
            Socket socket = new Socket(hostName, PORT_NUMBER);
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Client is connecting to " + hostName + ":" + PORT_NUMBER);
            String userInput;
            while ((userInput = input.readLine()) != null) {
                System.out.println("Sending to server: " + userInput);
                toServer.println(userInput);
                System.out.println("Received from server: " + fromServer.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }

}