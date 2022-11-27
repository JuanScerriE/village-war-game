import map.Map;
import village.Village;

// Standard Library
import java.util.Scanner;

public class Game {
    private Map _map;

    public void start() {
        setup();
        loop();
    }

    private void setup() {
        try (Scanner scanner = new Scanner(System.in)) {
        }
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
        for (var village : _map.getVillages()) {
            village
                    .friendlyTroopArrival()
                    .enemyTroopArrival()
                    .resourceEarning()
                    .playerActions();
        }
    }

    private void marchingPhase() {

    }

    private void endRound() {

    }

    private void end() {
        Village winningVillage = _map.getVillages().get(0);
    }
}
