package com.shuvzero.trips.server;

import com.shuvzero.trips.lobby.Lobby;
import com.shuvzero.trips.lobby.Profile;
import com.shuvzero.trips.message.ActionType;
import com.shuvzero.trips.message.LobbyMessage;
import com.shuvzero.trips.message.Message;

import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Server {

    public static final int PORT_NUMBER = 6667;
    public static final int MIN_ID = 10000;
    public static final int MAX_ID = 99999;

    private static final Random random = new Random();


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
                new ClientHandler(this, clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + PORT_NUMBER + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public Message createLobby(LobbyMessage request) {
        int id = generateId();
        System.out.println("Generated id: " + id);

        Lobby lobby = new Lobby();
        for(String name: request.getPlayers())
        lobby.addProfile(new Profile(name, true));
        serverGames.put(id, new ServerGame(lobby));

        LobbyMessage response = new LobbyMessage(ActionType.CREATE_GAME);
        response.setCode(id);
        response.setPlayers(Collections.singletonList(Profile.DEFAULT_PLAYER_NAME));
        return response;
    }

    private int generateId() {
        for(int attempt = 0; attempt < 100; attempt++) {
            int id = MIN_ID + random.nextInt(MAX_ID - MIN_ID + 1);
            if (!serverGames.containsKey(id)) {
                return id;
            }
        }
        return -1;
    }

}