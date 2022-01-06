package com.shuvzero.trips.server;

import com.shuvzero.trips.lobby.Lobby;
import com.shuvzero.trips.message.TechMessage;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static final int PORT_NUMBER = 6667;
    public static final int MIN_ID = 10000;
    public static final int MAX_ID = 99999;


    private Map<Integer, ServerGame> serverGames;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        System.out.println("Starting server on port: " + PORT_NUMBER);
        serverGames = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            System.out.println("Server is listening on port: " + PORT_NUMBER);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client is connected");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + PORT_NUMBER + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private void createLobby(TechMessage message) {
        Lobby lobby = new Lobby();
        int id = generateId();
        //lobbies.put(id, lobby);
        //add player
    }

    private int generateId() {
        //do we need both lobbies and games?

        //5 digits code
        //check this code is not used yet
        return 0;
    }

}