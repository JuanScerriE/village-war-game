package player;

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

        boolean repeat = false;

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

        switch (option) {
            case 1:
                if (!village.buildAcademy()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 2:
                if (!village.buildFoundation()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 3:
                if (!village.buildArena()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 4:
                if (!village.buildFarm()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 5:
                if (!village.buildMine()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 6:
                if (!village.buildManaTower()) {
                    System.out.println("Not enough resources!");
                }
                break;
        }
    }

    public void upgradeOption(Village village) {
        int option = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        boolean repeat = false;

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

        switch (option) {
            case 1:
                if (!village.upgradeAcademy()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 2:
                if (!village.upgradeFoundation()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 3:
                if (!village.upgradeArena()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 4:
                if (!village.upgradeFarm()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 5:
                if (!village.upgradeMine()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 6:
                if (!village.upgradeManaTower()) {
                    System.out.println("Not enough resources!");
                }
                break;
        }
    }

    public void trainOption(Village village) {
        int option = 0;

        // TODO: Print the cost each building

        Scanner scanner = new Scanner(System.in);

        boolean repeat = false;

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

        switch (option) {
            case 1:
                if (!village.buildAcademy()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 2:
                if (!village.buildFoundation()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 3:
                if (!village.buildArena()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 4:
                if (!village.buildFarm()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 5:
                if (!village.buildMine()) {
                    System.out.println("Not enough resources!");
                }
                break;
            case 6:
                if (!village.buildManaTower()) {
                    System.out.println("Not enough resources!");
                }
                break;
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
