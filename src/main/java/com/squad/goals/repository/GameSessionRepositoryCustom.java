package com.squad.goals.repository;

import java.util.List;

import com.squad.goals.model.GameMap;
import com.squad.goals.model.Player;

public interface GameSessionRepositoryCustom {

	List<Player> getPlayersBySessionId(Long sessionId);

	GameMap getMapBySessionId(Long sessionId);
}
