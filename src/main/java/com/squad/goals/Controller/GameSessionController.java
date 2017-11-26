package com.squad.goals.Controller;

import com.squad.goals.model.GetDataResponse;
import com.squad.goals.service.GameSessionService;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/gameSession", produces = "application/json")
public class GameSessionController {

	@Autowired
	private GameSessionService gameSessionService;

    @RequestMapping(value = "/getData/{sessionId}", method = RequestMethod.POST)
    public GetDataResponse getGameData(HttpRequest request, @PathVariable Long sessionId) {
        return gameSessionService.getGameSession(sessionId);
    }
}
