package com.squad.goals.repository;

import java.util.List;

import com.squad.goals.model.Map;
import com.squad.goals.model.Player;

public interface GameSessionRepositoryCustom {
	
	List<Player> getPlayersBySessionId(Long sessionId);

	Map getMapBySessionId(Long sessionId);
}
