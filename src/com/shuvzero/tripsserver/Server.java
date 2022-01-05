package com.shuvzero.tripsserver;

import com.shuvzero.trips.lobby.Lobby;
import com.shuvzero.trips.message.Message;
import com.shuvzero.trips.model.game.Game;

import java.net.*;
import java.io.*;
import java.util.Map;

public class Server {

    public static final int PORT_NUMBER = 6667;

    private Map<Integer, Lobby> lobbies;
    private Map<Integer, Game> games;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        System.out.println("Starting server on port: " + PORT_NUMBER);
        try (
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                Socket clientSocket = serverSocket.accept();
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Client is connected");
            String clientMessage;
            while ((clientMessage = fromClient.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
                Message serverMessage = handle(clientMessage);
                System.out.println("Sending to client: " + serverMessage);
                toClient.println(serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + PORT_NUMBER + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private void createLobby() {
        Lobby lobby = new Lobby();
        //lobbies.put();
    }

    private Message handle(String message) {
        //decode message
        return null;
    }

}