package com.squad.goals.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.squad.goals.model.GameMap;
import com.squad.goals.model.Player;
import com.squad.goals.repository.GameSessionRepositoryCustom;

public class GameSessionRepositoryImpl implements GameSessionRepositoryCustom {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void init(){
		dataSource = getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Player> getPlayersBySessionId(Long sessionId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * FROM (select * FROM (SELECT " +
						" p.recording_id, " +
						" p.player_id, " +
						" t.pos_x as locationX, " +
						" t.pos_y as locationY, " +
						" t.timestamp " +
						"FROM ticks t " +
						" INNER JOIN recording_session r ON (t.recording_id = r.recording_id AND r.session_id = ?) " +
						" INNER JOIN player_session p ON (t.recording_id = p.recording_id) " +
						"limit 500000) s " +
						"ORDER BY s.timestamp) g limit 100";
		
		return jdbcTemplate.query(sql, new GameSessionWrapper(), sessionId);
	}
	
	private static final class GameSessionWrapper implements RowMapper<Player> {
		public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
			Player player = new Player();
			player.setTimestamp(rs.getLong("timestamp"));
			player.setPlayerId(rs.getLong("player_id"));
			player.setRecordingId(rs.getLong("recording_id"));
			player.setLocationX(rs.getLong("locationX"));
			player.setLocationY(rs.getLong("locationY"));
			
			return player;
		}
	}
	
	@Override
	public GameMap getMapBySessionId(Long sessionId) {
		String sql = "SELECT " +
						" m.index, " +
						" m.map_name, " +
						" cast(substring(m.server_coor FROM '-?[0-9]+.') as double precision) as corner0x, " +
						" m.\"Corner0_y\" as corner0y, " +
						" m.\"Corner1_x\" as corner1x, " +
						" m.\"Corner1_y\" as corner1y " +
						"FROM map_coordinates m " +
						" INNER JOIN server_session s ON (s.map_name = m.map_name AND s.session_id = ?) " +
						"limit 1";
		return jdbcTemplate.queryForObject(sql, new GameMapWrapper(), sessionId);
	}

	private static final class GameMapWrapper implements RowMapper<GameMap> {
		public GameMap mapRow(ResultSet rs, int rowNum) throws SQLException {
			GameMap map = new GameMap();
			map.setIndex(rs.getLong("index"));
			map.setName(rs.getString("map_name"));
			map.setCorner0x(rs.getDouble("corner0x"));
			map.setCorner0y(rs.getDouble("corner0y"));
			map.setCorner1x(rs.getDouble("corner1x"));
			map.setCorner1y(rs.getDouble("corner1y"));
			
			return map;
		}
	}

	public static DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:postgresql://kamu-junction-vm3.kamu.gg:32768/postgres");
		dataSource.setUsername("kamu");
		dataSource.setPassword("becomeSOUNDbeat");

		return dataSource;
	}
}
