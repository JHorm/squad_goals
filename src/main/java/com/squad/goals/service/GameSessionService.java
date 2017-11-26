package com.squad.goals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad.goals.model.GameMap;
import com.squad.goals.model.GetDataResponse;
import com.squad.goals.model.Player;
import com.squad.goals.model.Tick;
import com.squad.goals.repository.GameSessionRepository;

@Service
public class GameSessionService {

	private final GameSessionRepository gameSessionRepository;

	@Autowired
	public GameSessionService(GameSessionRepository gameSessionRepository) {
		this.gameSessionRepository = gameSessionRepository;
	}

	private final Logger log = LoggerFactory.getLogger(getClass());

	public GetDataResponse getGameSession(Long sessionId) {
	    List<Player> players = gameSessionRepository.getPlayersBySessionId(sessionId);
        TreeMap<Long, List<Player>> timeSets = new TreeMap<>();
        GameMap gameMap = gameSessionRepository.getMapBySessionId(sessionId);

		for (Player player : players) {
			if (timeSets.containsKey(player.getTimestamp())) {
				timeSets.get(player.getTimestamp()).add(player);
			} else {
				List<Player> playerList = new ArrayList<>();
				playerList.add(player);
				timeSets.put(player.getTimestamp(), playerList);
			}
		}

        List<Tick> ticks = new ArrayList<>();

        for(Map.Entry<Long,List<Player>> entry : timeSets.entrySet()) {
            Tick tick = new Tick();
            tick.setTimeStamp(entry.getKey());
            tick.setPlayers(entry.getValue());
            ticks.add(tick);
        }

        GetDataResponse dataResponse = new GetDataResponse();
        dataResponse.setTicks(ticks);
        dataResponse.setGameMap(gameMap);

        return dataResponse;
	}
}
