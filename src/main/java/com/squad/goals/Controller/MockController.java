package com.squad.goals.Controller;

import com.squad.goals.model.GetDataResponse;
import com.squad.goals.model.Map;
import com.squad.goals.model.Player;
import com.squad.goals.model.Tick;

import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/api/mock", produces = "application/json")
public class MockController {
    private static Random random;
    private static Set<String> nameSet;

    @PostConstruct
    private void init() {
        random = new Random();
        nameSet = new HashSet<>();
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public GetDataResponse getGameData(HttpRequest request) {
        return generateRandomDataSet(1500000000, 10, 10);
    }

    private Player generateRandomPlayer(String name) {
        Player player = new Player();
        player.setLocationX(random.nextDouble() * random.nextInt(1000));
        player.setLocationY(random.nextDouble() * random.nextInt(1000));
        player.setPlayerName(name);

        return player;
    }

    private String generateRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < length) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            stringBuilder.append(SALTCHARS.charAt(index));
        }
        return stringBuilder.toString();
    }

    private Tick generateRandomTick(int numberOfPlayers, long timestamp) {
        Tick tick = new Tick();
        List<Player> players = new ArrayList<>();

        if (nameSet.isEmpty()) {
            for (int i = 0; i < numberOfPlayers; i++) {
                nameSet.add(getName(i));
            }
        }

        nameSet.forEach(name -> players.add(generateRandomPlayer(name)));

        tick.setPlayers(players);
        tick.setTimeStamp(timestamp);

        return tick;
    }

    private GetDataResponse generateRandomDataSet(long statrtTime, int numberOfTicks, int numberOfPlayers) {
        GetDataResponse dataResponse = new GetDataResponse();

        dataResponse.setMap(generateRandomMap());
        dataResponse.setTicks(generateRandomTicks(statrtTime, numberOfTicks, numberOfPlayers));

        return dataResponse;
    }

    private List<Tick> generateRandomTicks(long statrtTime, int numberOfTicks, int numberOfPlayers) {
        List<Tick> ticks = new ArrayList<>();

        for (int i = 0; i < numberOfTicks; i++) {
            ticks.add(generateRandomTick(numberOfPlayers, statrtTime + i * 500));
        }

        return ticks;
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

    private Map generateRandomMap() {
        Map map = new Map();

        map.setCorner0x(random.nextDouble() * (random.nextDouble() > 0.5 ? -1 : 1) * random.nextInt(20000));
        map.setCorner0y((random.nextDouble() * random.nextInt()) + map.getCorner0x());

        map.setCorner1x(random.nextDouble() * (random.nextDouble() > 0.5 ? -1 : 1) * random.nextInt(20000));
        map.setCorner1y((random.nextDouble() * random.nextInt()) + map.getCorner0x());

        map.setName(generateRandomString(10));

        return map;
    }
}



