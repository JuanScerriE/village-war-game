import map.Dimensions;
import map.Point;
import map.Map;

import player.specific.HumanPlayer;
import player.specific.AIPlayer;

import tick.Tick;
import village.Village;

// Standard Library
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Map _map = Map.getInstance();

    // Tick singleton keeps track of the number of elapsed game
    // loop cycles
    private final Tick _tick = Tick.getInstance();

    public void start() {
        setup();
        loop();
    }

    private void setup() {
        int numOfHumanPlayers = 0;
        int numOfAIPlayers = 0;

        boolean repeat;

        Scanner scanner = new Scanner(System.in);

        // Get number of human players
        do {
            repeat = false;

            System.out.print("Enter the number of human players\n> ");

            try {
                numOfHumanPlayers = scanner.nextInt();

                if (numOfHumanPlayers < 1) {
                    System.out.println("Invalid: must be a greater than or equal to 1");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: must be a integer");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        // Get number of AI players
        do {
            repeat = false;

            System.out.print("Enter the number of AI players\n> ");

            try {
                numOfAIPlayers = scanner.nextInt();

                if (numOfHumanPlayers == 1) {
                    if (numOfAIPlayers < 1) {
                        System.out.println("Invalid: must be a greater than or equal to 1");
                        repeat = true;
                    }
                } else {
                    if (numOfAIPlayers < 0) {
                        System.out.println("Invalid: must be a greater than or equal to 0");
                        repeat = true;
                    }
                }

                scanner.nextLine();
            } catch (Exception exception) {
                System.out.println("Invalid: must be an integer");
                repeat = true;
            }
        } while (repeat);

        // Calculate map dimensions
        int villageRadius = 20;

        int length = villageRadius * (int)Math.ceil(Math.sqrt(numOfHumanPlayers + numOfAIPlayers));

        Dimensions dimensions = new Dimensions(length, length);

        List<Village> villages = new LinkedList<>();

        _map.setDimensionsRef(dimensions)
            .setVillagesRef(villages)
            .setArmiesRef(new LinkedList<>());

        // Create players and villages
        for (int i = 0; i < numOfHumanPlayers; i++) {
            villages.add(new Village(new HumanPlayer("Human " + (i + 1))));
        }

        for (int i = 0; i < numOfAIPlayers; i++) {
            villages.add(new Village(new AIPlayer("AI " + (i + 1))));
        }

        // Prep for location calculations
        boolean locationSuitable;
        Point location;

        for (var village : villages) {
            LinkedList<Village> enemyVillages = new LinkedList<>(villages);
            // Improtantly, the village is not part of the list
            // of enemy villages. Additionally, a new enemy list
            // is required for each village since it is
            // different for each village.
            enemyVillages.remove(village);

            // Calculate village location
            do {
                locationSuitable = true;
                location = dimensions.randomPoint();

                // Ensure villages are adequately spaced
                for (var enemyVillage : enemyVillages) {
                    if (
                        enemyVillage.getLocation() != null &&
                        location.distanceFrom(enemyVillage.getLocation()) < villageRadius
                    ) {
                        locationSuitable = false;
                        break;
                    }
                }
            } while (!locationSuitable);

            // Set village properties
            village
                .setEnemyVillages(enemyVillages)
                .setLocation(location);
        }
    }

    private void loop() {
        while (_map.villagesRef().size() > 1) {
            turnPhase();
            marchingPhase();
            _tick.increment();
        }

        end();
    }

    private void removeArmiesOfDefeatedVillage(Village village) {
        for (var army : new LinkedList<>(_map.armiesRef())) {
            if (army.isFriendly(village)) {
                _map.armiesRef().remove(army);
            }
        }
    }

    private void turnPhase() {
        for (var village : new LinkedList<>(_map.villagesRef())) {
            village
                .friendlyTroopArrival()
                .enemyTroopArrival();

            // Check if village is destroyed. In which case
            // mark everything related to the village for GC
            if (village.isDestroyed()) {
                village.removeSelfFromEnemies();
                removeArmiesOfDefeatedVillage(village);
                _map.villagesRef().remove(village);
            } else {
                village
                    .resourceEarning()
                    .playerActions();
            }
        }
    }

    private void marchingPhase() {
        for (var army : _map.armiesRef()) {
            army.march();
        }
    }

    private void end() {
        Village winningVillage = _map.villagesRef().get(0);

        System.out.println("The winning player is: " + winningVillage.getPlayer().getName());
    }
}
