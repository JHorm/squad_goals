package com.squad.goals.Controller;

import java.util.List;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.squad.goals.model.Player;
import com.squad.goals.service.PlayerService;

@RestController
@RequestMapping(value = "/api/gameSession", produces = "application/json")
public class GameSessionController {
	
	@Autowired
	private PlayerService playerService;

    @RequestMapping(value = "/getData/{sessionId}", method = RequestMethod.POST)
    public List<Player> getGameData(HttpRequest request, @PathVariable Long sessionId) {
        return playerService.getPlayersBySessionId(sessionId);
    }
}
