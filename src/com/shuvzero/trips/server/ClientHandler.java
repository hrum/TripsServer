package com.shuvzero.trips.server;

import com.shuvzero.trips.message.ActionType;
import com.shuvzero.trips.message.LobbyMessage;
import com.shuvzero.trips.message.Message;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Server server;
    private Socket clientSocket;

    public ClientHandler(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientMessage;
            while ((clientMessage = fromClient.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
                Message serverMessage = handle(clientMessage);
                System.out.println("Sending to client: " + serverMessage);
                toClient.println(serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to handle client");
            System.out.println(e.getMessage());
        }
    }

    private Message handle(String input) {
        LobbyMessage message = (LobbyMessage) Message.decode(input);
        switch (message.getActionType()) {
            case CREATE_GAME:
                server.createLobby(message);
                break;
        }
        return null;
    }
}
