package com.squad.goals.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squad.goals.model.Map;
import com.squad.goals.model.Player;
import com.squad.goals.model.Tick;
import com.squad.goals.repository.PlayerRepository;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public List<Tick> getPlayersBySessionId(Long sessionId) {
	    List<Player> players = playerRepository.getPlayersBySessionId(sessionId);
        TreeMap<Long, List<Player>> timeSets = new TreeMap<>();

        players.forEach(player -> {
            if (timeSets.containsKey(player.getTimestamp())) {
                timeSets.get(player.getTimestamp()).add(player);
            } else {
                List<Player> playerList = new ArrayList<>();
                playerList.add(player);
                timeSets.put(player.getTimestamp(), playerList);
            }
        });

        List<Tick> ticks = new ArrayList<>();

        timeSets.forEach((key, value) -> {
            Tick tick = new Tick();
            tick.setTimeStamp(key);
            tick.setPlayers(value);

            ticks.add(tick);
        });

        return ticks;
	}
}
