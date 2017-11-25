package com.squad.goals.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.squad.goals.model.Player;
import com.squad.goals.repository.PlayerRepositoryCustom;

public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override public List<Player> getPlayersBySessionId(Long sessionId) {
		return entityManager.createNativeQuery(
				"select * FROM (select * FROM (SELECT " +
						" ps.recording_id, " +
						" ps.player_id, " +
						" t.pos_x as locationX, " +
						" t.pos_y as locationY, " +
						" t.timestamp " +
						"FROM ticks t " +
						" INNER JOIN recording_session rs ON (t.recording_id = rs.recording_id AND rs.session_id = :sessionId) " +
						" INNER JOIN player_session ps ON (t.recording_id = ps.recording_id) " +
						"limit 500000) s " +
						"ORDER BY s.timestamp) g " +
						"limit 100", Player.class)
				.setParameter("sessionId", sessionId)
				.getResultList();
	}
}
