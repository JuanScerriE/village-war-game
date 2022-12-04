package player;

import village.Status;
import village.Village;

// TODO(juan): improve the AI by possibly factoring in cost of actions

public class AIPlayer extends Player {

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

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
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

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
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

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void attackEnemyVillage(Village village) {
        int villageNum = (int)Math.ceil((village.getEnemyVillages().size() - 1)*Math.random() + 1);

        int numOfWizards = (int)Math.ceil((village.getTroops().getWizards().size() - 1)*Math.random() + 1);
        int numOfBrawlers = (int)Math.ceil((village.getTroops().getBrawlers().size() - 1)*Math.random() + 1);
        int numOfScouts = (int)Math.ceil((village.getTroops().getScouts().size() - 1)*Math.random() + 1);

        Status status = village.attackVillage(villageNum - 1, numOfWizards, numOfBrawlers, numOfScouts);

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    @Override
    public void actions(Village village) {
        boolean nextPlayer = false;

        int option = 0;

        while (!nextPlayer) {
            if (0.4 < Math.random()) {
                buildOption(village);
            }

            if (0.6 < Math.random()) {
                upgradeOption(village);
            }

            if (!village.getTroops().isEmpty() && 0.4 < Math.random()) {
                trainOption(village);
            }

            if (!village.getTroops().isEmpty() && 0.6 < Math.random()) {
                attackEnemyVillage(village);
            }

            if (0.3 < Math.random()) {
                nextPlayer = true;
            }
        }
    }
}
