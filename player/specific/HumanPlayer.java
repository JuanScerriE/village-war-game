package player.specific;

import building.resource.specific.Farm;
import building.resource.specific.ManaTower;
import building.resource.specific.Mine;
import building.troop.specific.Academy;
import building.troop.specific.Arena;
import building.troop.specific.Foundation;

import troop.types.specific.Brawler;
import troop.types.specific.Scout;
import troop.types.specific.Wizard;

import village.Status;
import village.Village;

import player.Player;

// Standard Library
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    public void buildOption(Village village) {
        int option = 0;

        printCostsOfBuilding();

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                    """
                            1. Build Academy
                            2. Build Foundation
                            3. Build Arena
                            4. Build Farm
                            5. Build Mine
                            6. Build Mana Tower
                            7. Go Back
                            >\s""");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 7) {
                    System.out.println("Invalid: input must be between 1 and 7 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        Status status = switch (option) {
            case 1 -> village.buildAcademy();
            case 2 -> village.buildFoundation();
            case 3 -> village.buildArena();
            case 4 -> village.buildFarm();
            case 5 -> village.buildMine();
            case 6 -> village.buildManaTower();
            case 7 -> Status.SUCCESS;

            default -> Status.UNREACHABLE;
        };

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void upgradeOption(Village village) {
        int option = 0;

        printCostsOfUpgrades();

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                    """
                            1. Upgrade Academy
                            2. Upgrade Foundation
                            3. Upgrade Arena
                            4. Upgrade Farm
                            5. Upgrade Mine
                            6. Upgrade Mana Tower
                            7. Go Back
                            >\s""");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 7) {
                    System.out.println("Invalid: input must be between 1 and 7 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        Status status = switch (option) {
            case 1 -> village.upgradeAcademy();
            case 2 -> village.upgradeFoundation();
            case 3 -> village.upgradeArena();
            case 4 -> village.upgradeFarm();
            case 5 -> village.upgradeMine();
            case 6 -> village.upgradeManaTower();
            case 7 -> Status.SUCCESS;

            default -> Status.UNREACHABLE;
        };

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void trainOption(Village village) {
        int option = 0;

        printCostsOfTraining();

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                    """
                            1. Train Wizards
                            2. Train Brawlers
                            3. Train Scouts
                            4. Go Back
                            >\s""");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 4) {
                    System.out.println("Invalid: input must be between 1 and 4 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        if (option == 4) {
            return;
        }

        int numOfTroops = 1;

        do {
            repeat = false;

            System.out.print("Input number of troops\n> ");

            try {
                numOfTroops = scanner.nextInt();

                if (numOfTroops < 1) {
                    System.out.println("Invalid: input must be greater than or equal to 1");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

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
        int villageNum = 0;

        Scanner scanner = new Scanner(System.in);

        village.printEnemyVillages();

        boolean repeat;

        do {
            repeat = false;

            System.out.print("Input village number\n> ");

            try {
                villageNum = scanner.nextInt();

                if (villageNum < 1 || villageNum > village.getEnemyVillages().size()) {
                    System.out.println("Invalid: input must be between 1 and " + village.getEnemyVillages().size() +
                            " inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        village.printStationedTroopsStats();

        int numOfWizards = 1;

        do {
            repeat = false;

            System.out.print("Input number of wizards\n> ");

            try {
                numOfWizards = scanner.nextInt();

                if (numOfWizards < 0 || numOfWizards > village.getStation().sizeOfCategory(Wizard.class)) {
                    System.out.println("Invalid: input must be between 0 and " + village.getStation().sizeOfCategory(Wizard.class));
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        int numOfBrawlers = 1;

        do {
            repeat = false;

            System.out.print("Input number of brawlers\n> ");

            try {
                numOfBrawlers = scanner.nextInt();

                if (numOfBrawlers < 0 || numOfBrawlers > village.getStation().sizeOfCategory(Brawler.class)) {
                    System.out.println("Invalid: input must be between 0 and " + village.getStation().sizeOfCategory(Brawler.class));
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        int numOfScouts = 1;

        do {
            repeat = false;

            System.out.print("Input number of scouts\n> ");

            try {
                numOfScouts = scanner.nextInt();

                if (numOfScouts < 0 || numOfBrawlers > village.getStation().sizeOfCategory(Scout.class)) {
                    System.out.println("Invalid: input must be between 0 and " + village.getStation().sizeOfCategory(Scout.class));
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        Status status = village.attackVillage(villageNum - 1, numOfWizards, numOfBrawlers, numOfScouts);

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }
    public void printCostsOfBuilding() {
        System.out.println(
                "\nCost to build Farm: " + Farm.CostToBuild +
                "\nCost to build Mine: " + Mine.CostToBuild +
                "\nCost to build Mana Tower: " + ManaTower.CostToBuild +
                "\nCost to build Academy: " + Academy.CostToBuild +
                "\nCost to build Arena: " + Arena.CostToBuild +
                "\nCost to build Foundation: " + Foundation.CostToBuild
        );
    }

    public void printCostsOfUpgrades() {
        System.out.println(
                "\nCost to upgrade Farm: " + Farm.CostToUpgrade +
                "\nCost to upgrade Mine: " + Mine.CostToUpgrade +
                "\nCost to upgrade Mana Tower: " + ManaTower.CostToUpgrade +
                "\nCost to upgrade Academy: " + Academy.CostToUpgrade +
                "\nCost to upgrade Arena: " + Arena.CostToUpgrade +
                "\nCost to upgrade Foundation: " + Foundation.CostToUpgrade
        );
    }

    public void printCostsOfTraining() {
        System.out.println(
                "\nCost to train Wizard: " + Academy.CostToTrainTroop +
                "\nCost to train Brawler: " + Arena.CostToTrainTroop +
                "\nCost to train Scout: " + Foundation.CostToTrainTroop
        );
    }
    public void printCosts() {
        System.out.println("Costs:");
        printCostsOfBuilding();
        printCostsOfUpgrades();
        printCostsOfTraining();
    }

    @Override
    public void actions(Village village) {
        boolean nextPlayer = false;
        int option = 0;

        Scanner scanner = new Scanner(System.in);

        while (!nextPlayer) {
            boolean repeat;

            do {
                repeat = false;

                System.out.print(
                        """
                                Info:
                                1.  Print village stats              2.  Print stationed troops stats
                                3.  Print building stats             4.  Print costs
                                5.  Print enemy villages             6.  Print armies
                                Actions:
                                7.  Build                            8.  Upgrade
                                9.  Train                            10. Attack
                                11. Pass
                                >\s"""
                );

                try {
                    option = scanner.nextInt();

                    if (option < 1 || option > 11) {
                        System.out.println("Invalid: input must be between 1 and 11 inclusive");
                        repeat = true;
                    }
                } catch (Exception exception) {
                    System.out.println("Invalid: input must be a number");
                    repeat = true;
                }

                scanner.nextLine();
            } while (repeat);

            switch (option) {
                // Info
                case 1 -> village.printVillageStats();
                case 2 -> village.printStationedTroopsStats();
                case 3 -> village.printBuildingStats();
                case 4 -> printCosts();
                case 5 -> village.printEnemyVillages();
                case 6 -> village.printArmies();

                // Actions
                case 7 -> buildOption(village);
                case 8 -> upgradeOption(village);
                case 9 -> trainOption(village);
                case 10 -> attackEnemyVillage(village);
                case 11 -> nextPlayer = true;
            }
        }
    }

    @Override
    public void notify(String text) {
        System.out.println(text);
    }
}
