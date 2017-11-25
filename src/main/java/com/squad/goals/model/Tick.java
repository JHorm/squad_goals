package com.squad.goals.model;

import java.util.List;

public class Tick {
    private long timeStamp;
    private List<Player> players;

    public long getTimeStamp() {
        return timeStamp;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
