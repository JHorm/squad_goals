package com.squad.goals.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.squad.goals.model.Map;
import com.squad.goals.model.Player;
import com.squad.goals.repository.GameSessionRepositoryCustom;

public class GameSessionRepositoryImpl implements GameSessionRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override 
	public List<Player> getPlayersBySessionId(Long sessionId) {
		return entityManager.createNativeQuery(
				"select * FROM (select * FROM (SELECT " +
						" p.recording_id, " +
						" p.player_id, " +
						" t.pos_x as locationX, " +
						" t.pos_y as locationY, " +
						" t.timestamp " +
						"FROM ticks t " +
						" INNER JOIN recording_session r ON (t.recording_id = r.recording_id AND r.session_id = :sessionId) " +
						" INNER JOIN player_session p ON (t.recording_id = p.recording_id) " +
						"limit 500000) s " +
						"ORDER BY s.timestamp) g " +
						"limit 100", Player.class)
				.setParameter("sessionId", sessionId)
				.getResultList();
	}

	@Override
	public Map getMapBySessionId(Long sessionId) {
		return (Map) entityManager.createNativeQuery(
				"SELECT " +
						" m.index, " +
						" m.map_name as name, " +
						" cast(substring(m.server_coor FROM '[0-9]+.') as double precision) as corner0x, " +
						" m.\"Corner0_y\" as corner0y, " +
						" m.\"Corner1_x\" as corner1x, " +
						" m.\"Corner1_y\" as corner1y " +
						"FROM map_coordinates m " +
						" INNER JOIN server_session s ON (s.map_name = m.map_name AND s.session_id = :sessionId) " +
						"limit 1", Map.class)
				.setParameter("sessionId", sessionId)
				.getSingleResult();
	} 
}
