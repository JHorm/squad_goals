package com.squad.goals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squad.goals.model.Player;
import com.squad.goals.repository.PlayerRepository;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;

	@Autowired 
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public List<Player> getPlayersBySessionId(Long sessionId) {
		return playerRepository.getPlayersBySessionId(sessionId);
	}
}
