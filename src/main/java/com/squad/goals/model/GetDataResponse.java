package com.squad.goals.model;

import java.util.List;

public class GetDataResponse {
    GameMap gameMap;
    List<Tick> ticks;

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Tick> getTicks() {
        return ticks;
    }

    public void setTicks(List<Tick> ticks) {
        this.ticks = ticks;
    }
}
