package player.specific;

import player.Player;
import tick.Tick;
import troop.types.specific.Brawler;
import troop.types.specific.Scout;
import troop.types.specific.Wizard;
import village.Status;
import village.Village;

public class AIPlayer extends Player {

    private final Tick _tick = Tick.getInstance();

    public AIPlayer(String name) {
        super(name);
    }

    public void buildOption(Village village) {
        int option = (int)Math.ceil((6 - 1)*Math.random() + 1);

        Status status = switch (option) {
            case 1 -> village.buildAcademy();
            case 2 -> village.buildFoundation();
            case 3 -> village.buildArena();
            case 4 -> village.buildFarm();
            case 5 -> village.buildMine();
            case 6 -> village.buildManaTower();

            default -> Status.UNREACHABLE;
        };
    }

    public void upgradeOption(Village village) {
        int option = (int)Math.ceil((6 - 1)*Math.random() + 1);

        Status status = switch (option) {
            case 1 -> village.upgradeAcademy();
            case 2 -> village.upgradeFoundation();
            case 3 -> village.upgradeArena();
            case 4 -> village.upgradeFarm();
            case 5 -> village.upgradeMine();
            case 6 -> village.upgradeManaTower();

            default -> Status.UNREACHABLE;
        };
    }

    public void trainOption(Village village) {
        int option = (int)Math.ceil((3 - 1)*Math.random() + 1);

        // Train between 1 to 10 troops at once.
        int numOfTroops = (int)Math.ceil((10 - 1)*Math.random() + 1);

        Status status = switch (option) {
            case 1 -> village.trainWizards(numOfTroops);
            case 2 -> village.trainBrawlers(numOfTroops);
            case 3 -> village.trainScouts(numOfTroops);

            default -> Status.UNREACHABLE;
        };
    }

    public void attackEnemyVillage(Village village) {
        int villageNum = (int)Math.ceil((village.getEnemyVillages().size() - 1)*Math.random() + 1);

        int numOfWizards = (int)Math.ceil((village.getStation().sizeOfCategory(Wizard.class) - 1)*Math.random() + 1);
        int numOfBrawlers = (int)Math.ceil((village.getStation().sizeOfCategory(Brawler.class) - 1)*Math.random() + 1);
        int numOfScouts = (int)Math.ceil((village.getStation().sizeOfCategory(Scout.class) - 1)*Math.random() + 1);

        Status status = village.attackVillage(villageNum - 1, numOfWizards, numOfBrawlers, numOfScouts);
    }

    public void startGame(Village village)  {
        if (0.4 < Math.random()) {
            buildOption(village);
        }

        if (0.6 < Math.random()) {
            upgradeOption(village);
        }

        if (!village.getStation().isEmpty() && 0.4 < Math.random()) {
            trainOption(village);
        }
    }

    public void middleGame(Village village) {
        if (0.7 < Math.random()) {
            buildOption(village);
        }

        if (0.5 < Math.random()) {
            upgradeOption(village);
        }

        if (!village.getStation().isEmpty() && 0.4 < Math.random()) {
            trainOption(village);
        }

        if (!village.getStation().isEmpty() && 0.4 < Math.random()) {
            attackEnemyVillage(village);
        }
    }

    public void endGame(Village village) {
        if (0.4 < Math.random()) {
            buildOption(village);
        }

        if (0.6 < Math.random()) {
            upgradeOption(village);
        }

        if (!village.getStation().isEmpty() && 0.4 < Math.random()) {
            trainOption(village);
        }

        if (!village.getStation().isEmpty() && 0.3 < Math.random()) {
            attackEnemyVillage(village);
        }
    }

    @Override
    public void actions(Village village) {
        boolean nextPlayer = false;

        while (!nextPlayer) {
            if (_tick.lessThan(20)) {
                startGame(village);
            } else if (_tick.lessThan(60)) {
                middleGame(village);
            } else {
                endGame(village);
            }

            if (0.3 < Math.random()) {
                nextPlayer = true;
            }
        }
    }

    @Override
    public void notify(String text) {
        return; // stub
    }
}
