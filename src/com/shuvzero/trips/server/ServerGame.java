package com.shuvzero.trips.server;

import com.shuvzero.trips.lobby.Lobby;
import com.shuvzero.trips.model.game.Game;

public class ServerGame {

    private Lobby lobby;
    private Game game;

    public ServerGame(Lobby lobby) {
        this.lobby = lobby;
    }

    public Game getGame() {
        return game;
    }

    public Lobby getLobby() {
        return lobby;
    }
}
