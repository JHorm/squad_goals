package com.squad.goals.Controller;

import com.squad.goals.model.Player;
import com.squad.goals.model.Tick;

import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class BaseController {
    private static Random random = new Random();

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<Tick> getGameData(HttpRequest request) {
        return generateRandomDataSet(1500000000, 1000, 50);
    }

    private Player generateRandomPlayer() {
        Player player = new Player();
        player.setLocationX(random.nextDouble());
        player.setLocationY(random.nextDouble());
        player.setPlayer_name(generateRandomString(10));

        return player;
    }

    private String generateRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < length) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            stringBuilder.append(SALTCHARS.charAt(index));
        }
        return stringBuilder.toString();
    }

    private Tick generateRandomTick(int numberOfPlayers, long timestamp) {
        Tick tick = new Tick();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(generateRandomPlayer());
        }

        tick.setPlayers(players);
        tick.setTimeStamp(timestamp);

        return tick;
    }

    private List<Tick> generateRandomDataSet(long statrtTime, int numberOfTicks, int numberOfPlayers) {
        List<Tick> ticks = new ArrayList<>();

        for (int i = 0; i < numberOfTicks; i++) {
            ticks.add(generateRandomTick(numberOfPlayers, statrtTime + i/2));
        }

        return ticks;
    }
}
