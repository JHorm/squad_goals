package com.squad.goals.repository;

import java.util.List;

import com.squad.goals.model.Player;

public interface PlayerRepositoryCustom {
	
	List<Player> getPlayersBySessionId(Long sessionId);
}
