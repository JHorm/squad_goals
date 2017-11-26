package com.squad.goals.service;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad.goals.model.GameMap;
import com.squad.goals.model.GetDataResponse;
import com.squad.goals.model.Player;
import com.squad.goals.model.Tick;
import com.squad.goals.repository.GameSessionRepository;

@Service
public class GameSessionService {

	private final GameSessionRepository gameSessionRepository;

	@Autowired
	public GameSessionService(GameSessionRepository gameSessionRepository) {
		this.gameSessionRepository = gameSessionRepository;
	}

	private final Logger log = LoggerFactory.getLogger(getClass());


	public GetDataResponse getGameSession(Long sessionId) {
	    List<Player> players = gameSessionRepository.getPlayersBySessionId(sessionId);
        TreeMap<Long, List<Player>> timeSets = new TreeMap<>();
        GameMap gameMap = gameSessionRepository.getMapBySessionId(sessionId);

		int i = 0;
		for (Player player : players) {
			if (timeSets.containsKey(player.getTimestamp())) {
			    player.setPlayerName(getName(i++));
				timeSets.get(player.getTimestamp()).add(player);
			} else {
				List<Player> playerList = new ArrayList<>();
                player.setPlayerName(getName(i++));
				playerList.add(player);
				timeSets.put(player.getTimestamp(), playerList);
			}
			if (i < 70) i = 0;
		}

        List<Tick> ticks = new ArrayList<>();

        for(Map.Entry<Long,List<Player>> entry : timeSets.entrySet()) {
            Tick tick = new Tick();
            tick.setTimeStamp(entry.getKey());
            tick.setPlayers(entry.getValue());
            ticks.add(tick);
        }

        GetDataResponse dataResponse = new GetDataResponse();
        dataResponse.setTicks(ticks);
        dataResponse.setGameMap(gameMap);

        return dataResponse;
	}


    private static String getName(int id) {
        List<String> names = Arrays.asList("NinjasInPyjamas",
            "Mistake",
            "SomeTacos",
            "12Nuns",
            "AHungryPolarBear",
            "aDistraction",
            "XBox",
            "ShutDown",
            "RollingBarrelz",
            "Something",
            "AllGoodNamesRGone",
            "Error404",
            "CerealKillah",
            "WarHawk",
            "Kladenstien",
            "Audacity",
            "JackSparrow",
            "RuthlessSlayer",
            "InfernalHeir",
            "TheSilentBang",
            "DarkLord",
            "NoTolerance",
            "DexterzProtege",
            "BeoWulf",
            "LoneWalker",
            "SavageHorseman",
            "GunnerBomb",
            "CapnBloodBeard",
            "LastSamurai",
            "PetalPrincess",
            "FallenAngel",
            "Hraefn",
            "IMTooPrettyToDie",
            "CatWoman",
            "SniperFemme",
            "Zeldarian",
            "CursedWings",
            "IceQueen",
            "SongbirdFatale",
            "LadyPhantom",
            "WarriorPriestess",
            "DeathWish",
            "SeekNDestroy",
            "BegForMercy",
            "ElNino",
            "FreakingOblin",
            "NineTees",
            "EndlessFacepalms",
            "KungFuMonk",
            "BrainAxe",
            "PlzJustDie",
            "Gridlock",
            "AndKeySinister",
            "Chill",
            "AlQaholic",
            "HoofHearted",
            "666Disaster",
            "MasterGhostlyPresence");

        if (id > names.size()) {
            id = id - names.size();
            return names.get(id) + id;
        }
        return names.get(id);
    }
}
