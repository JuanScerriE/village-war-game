package player;

import village.Status;
import village.Village;

// Standard Library
import java.util.Scanner;


// TODO(juan): Add option to go back on submenus.
// TODO(juan): Print cost of things.
// TODO(juan): Print number of troops in the Village class.

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    public void buildOption(Village village) {
        int option = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                     "1. Build Academy\n"
                    +"2. Build Foundation\n"
                    +"3. Build Arena\n"
                    +"4. Build Farm\n"
                    +"5. Build Mine\n"
                    +"6. Build Mana Tower\n"
                    +"> ");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 6) {
                    System.out.println("Invalid: input must be between 1 and 6 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        Status status = Status.SUCCESS;

        switch (option) {
            case 1:
                status = village.buildAcademy();
                break;
            case 2:
                status = village.buildFoundation();
                break;
            case 3:
                status = village.buildArena();
                break;
            case 4:
                status = village.buildFarm();
                break;
            case 5:
                status = village.buildMine();
                break;
            case 6:
                status = village.buildManaTower();
                break;
        }

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void upgradeOption(Village village) {
        int option = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                     "1. Upgrade Academy\n"
                    +"2. Upgrade Foundation\n"
                    +"3. Upgrade Arena\n"
                    +"4. Upgrade Farm\n"
                    +"5. Upgrade Mine\n"
                    +"6. Upgrade Mana Tower\n"
                    +"> ");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 6) {
                    System.out.println("Invalid: input must be between 1 and 6 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

        Status status = Status.SUCCESS;

        switch (option) {
            case 1:
                status = village.upgradeAcademy();
                break;
            case 2:
                status = village.upgradeFoundation();
                break;
            case 3:
                status = village.upgradeArena();
                break;
            case 4:
                status = village.upgradeFarm();
                break;
            case 5:
                status = village.upgradeMine();
                break;
            case 6:
                status = village.upgradeManaTower();
                break;
        }

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void trainOption(Village village) {
        int option = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        boolean repeat;

        do {
            repeat = false;

            System.out.print(
                     "1. Train Wizards\n"
                    +"2. Train Brawlers\n"
                    +"3. Train Scouts\n"
                    +"> ");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 3) {
                    System.out.println("Invalid: input must be between 1 and 3 inclusive");
                    repeat = true;
                }
            } catch (Exception exception) {
                System.out.println("Invalid: input must be a number");
                repeat = true;
            }

            scanner.nextLine();
        } while (repeat);

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

        Status status = Status.SUCCESS;

        switch (option) {
            case 1:
                status = village.trainWizards(numOfTroops);
                break;
            case 2:
                status = village.trainBrawlers(numOfTroops);
                break;
            case 3:
                status = village.trainScouts(numOfTroops);
                break;
        }

        if (status != Status.SUCCESS) {
            status.printMessage();
        }
    }

    public void attackEnemyVillage(Village village) {
        int villageNum = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        village.PrintEnemyVillages();

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

        int numOfWizards = 1;

        do {
            repeat = false;

            System.out.print("Input number of wizards\n> ");

            try {
                numOfWizards = scanner.nextInt();

                if (numOfWizards < 0) {
                    System.out.println("Invalid: input must be greater than or equal to 0");
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

                if (numOfBrawlers < 0) {
                    System.out.println("Invalid: input must be greater than or equal to 0");
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

                if (numOfScouts < 0) {
                    System.out.println("Invalid: input must be greater than or equal to 0");
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

    @Override
    public void actions(Village village) {
        boolean nextPlayer = false;
        int option = 0;

        Scanner scanner = new Scanner(System.in);

        while (!nextPlayer) {
            boolean repeat = false;

            do {
                repeat = false;

                System.out.print(
                          "Info:                             Building/Training:\n"
                        + "1. Print village stats            5. Build\n"
                        + "2. Print stationed troops stats   6. Upgrade\n"
                        + "3. Print building stats           7. Train\n"
                        + "4. Print armies\n"
                        + "Attack Enemy Village:             Next:\n"
                        + "8. Print enemy villages           10. Next Player\n"
                        + "9. Attack enemy village\n"
                        + "> "
                );

                try {
                    option = scanner.nextInt();

                    if (option < 1 || option > 10) {
                        System.out.println("Invalid: input must be between 1 and 10 inclusive");
                        repeat = true;
                    }
                } catch (Exception exception) {
                    System.out.println("Invalid: input must be a number");
                    repeat = true;
                }

                scanner.nextLine();
            } while (repeat);

            switch (option) {
                case 1:
                    village.PrintVillageStats();
                    break;
                case 2:
                    village.PrintStationedTroopsStats();
                    break;
                case 3:
                    village.PrintBuildingStats();
                    break;
                case 4:
                    village.PrintArmies();
                    break;
                case 5:
                    buildOption(village);
                    break;
                case 6:
                    upgradeOption(village);
                    break;
                case 7:
                    trainOption(village);
                    break;
                case 8:
                    village.PrintEnemyVillages();
                    break;
                case 9:
                    attackEnemyVillage(village);
                    break;
                case 10:
                    nextPlayer = true;
                    break;
            }

            // Train / build commands

            // Attack commands
        }
    }
}
