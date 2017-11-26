package com.squad.goals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

	@Id
	@JsonIgnore
	@Column(name = "timestamp")
	private Long timestamp;
	
	@Column(name = "recording_id")
	private Long recordingId;
	
	@Column(name = "player_id")
	private Long playerId;
	
	@Column(name = "locationX")
    private double locationX;
	
	@Column(name = "locationY")
    private double locationY;

    @Transient
    private String playerName;

	public Long getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
