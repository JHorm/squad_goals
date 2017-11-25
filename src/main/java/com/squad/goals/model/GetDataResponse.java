package com.squad.goals.model;

import java.util.List;

public class GetDataResponse {
    Map map;
    List<Tick> ticks;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Tick> getTicks() {
        return ticks;
    }

    public void setTicks(List<Tick> ticks) {
        this.ticks = ticks;
    }
}
