import map.Dimensions;
import map.Point;
import map.Map;

import player.HumanPlayer;
import player.AIPlayer;

import village.Village;

// Standard Library
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Map _map = Map.getInstance();

    public void start() {
        setup();
        loop();
    }

    private void setup() {
        int numOfHumanPlayers = 0;
        int numOfAIPlayers = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            boolean repeat;

            // get number of real players
            do {
                repeat = false;

                System.out.print("Enter the number of human players: ");

                try {
                    numOfHumanPlayers = Integer.parseInt(scanner.nextLine());

                    if (numOfHumanPlayers < 1) {
                        invalid("must be a greater than or equal to 1");
                        repeat = true;
                    }
                } catch (Exception exception) {
                    invalid("must be a integer");
                    repeat = true;
                }
            } while (repeat);

            // get number of AI players
            do {
                repeat = false;

                System.out.print("Enter the number of AI players: ");

                try {
                    numOfAIPlayers = Integer.parseInt(scanner.nextLine());

                    if (numOfHumanPlayers == 1) {
                        if (numOfAIPlayers < 1) {
                            invalid("must be a greater than or equal to 1");
                            repeat = true;
                        }
                    } else {
                        if (numOfAIPlayers < 0) {
                            invalid("must be a greater than or equal to 0");
                            repeat = true;
                        }
                    }
                } catch (Exception exception) {
                    invalid("must be an integer");
                    repeat = true;
                }
            } while (repeat);
        }

        // calculate map dimensions
        Dimensions dimensions = calcMapDimensions(numOfHumanPlayers + numOfAIPlayers);

        _map
                .setDimensions(dimensions)
                .setArmies(new LinkedList<>());

        // create players and villages
        List<Village> villages = new LinkedList<>();

        for (int i = 0; i < numOfHumanPlayers; i++) {
            villages.add(new Village(new HumanPlayer(String.format("Human %d", i + 1))));
        }

        for (int i = 0; i < numOfAIPlayers; i++) {
            villages.add(new Village(new AIPlayer(String.format("AI %d", i + 1))));
        }


        // prep for location calculations
        int villageRadius = 20;

        boolean isLocationSuitable;

        Point location;

        for (var village : villages) {
            LinkedList<Village> enemyVillages = new LinkedList<>(villages);

            enemyVillages.remove(village);

            // calculate village location
            do {
                isLocationSuitable = true;

                location = dimensions.randomPoint();

                for (var enemyVillage : enemyVillages) {
                    if (enemyVillage.getLocation() != null) {
                        if (location.distanceFrom(enemyVillage.getLocation()) < villageRadius) {
                            isLocationSuitable = false;

                            break;
                        }
                    }
                }
            } while (!isLocationSuitable);

            // set village properties
            village
                    .setEnemyVillages(enemyVillages)
                    .setLocation(location);

        }

        _map.setVillages(villages);
    }

    private Dimensions calcMapDimensions(int numOfPlayers) {
        int villageRadius = 20;

        int length = villageRadius * (int)Math.ceil(Math.sqrt(numOfPlayers));

        return new Dimensions(length, length);
    }

    private void loop() {
        while (_map.getVillages().size() > 1) {
            startRound();

            turnPhase();
            marchingPhase();

            endRound();
        }

        end();
    }

    private void startRound() {

    }

    private void turnPhase() {
        List<Village> clonedVillages = new LinkedList<>(_map.getVillages());

        for (var village : clonedVillages) {
            village
                    .friendlyTroopArrival()
                    .enemyTroopArrival();
            if (village.isDestroyed()) {
                village.removeSelfFromOthers();
                _map.getVillages().remove(village);
            } else {
                village
                        .resourceEarning()
                        .playerActions();
            }
        }
    }

    private void marchingPhase() {

    }

    private void endRound() {

    }

    private void end() {
        Village winningVillage = _map.getVillages().get(0);
    }

    private void invalid(String message) {
        System.out.printf("Invalid: %s%n", message);
    }
}
