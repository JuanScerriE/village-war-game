package player;

import village.Status;
import village.Village;

// Standard Library
import java.util.Scanner;

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

            System.out.print("Input number of troops\n > ");

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
                        + "1. Print village stats            4. Build\n"
                        + "2. Print stationed troops stats   5. Upgrade\n"
                        + "3. Print building stats           6. Train\n"
                        + "Attack Enemy Village:             Next:\n"
                        + "7. Print enemy villages           9. Next Player\n"
                        + "8. Attack enemy village\n"
                        + "> "
                );

                try {
                    option = scanner.nextInt();

                    if (option < 1 || option > 9) {
                        System.out.println("Invalid: input must be between 1 and 9 inclusive");
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
                    buildOption(village);
                    break;
                case 5:
                    upgradeOption(village);
                    break;
                case 6:
                    trainOption(village);
                    break;
                case 7:
                    village.PrintEnemyVillages();
                    break;
                case 8:
                    break;
                case 9:
                    nextPlayer = true;
                    break;
            }

            // Train / build commands

            // Attack commands
        }
    }
}
