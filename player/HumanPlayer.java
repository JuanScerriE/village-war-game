package player;

import village.Village;

// Standard Library
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void actions(Village village) {
        boolean nextPlayer = false;
        int option = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.reset();

            while (!nextPlayer) {
                boolean repeat = false;

                do {
                    repeat = false;

                    System.out.print("Info:\n"
                            + "1. Print village stats\n"
                            + "2. Print stationed troops stats\n"
                            + "3. Print building stats\n"
                            + "Building/Training:\n"
                            + "4. Build Farm\n"
                            + "5. Upgrade Farm\n"
                            + "6. Build Mine\n"
                            + "7. Upgrade Mine\n"
                            + "8. Build Mana Tower\n"
                            + "9. Upgrade Mana Tower\n"
                            + "10. Build Academy\n"
                            + "11. Upgrade Academy\n"
                            + "12. Build Arena\n"
                            + "13. Upgrade Arena\n"
                            + "14. Build Foundation\n"
                            + "15. Upgrade Foundation\n"
                            + "16. Train Wizards\n"
                            + "17. Train Brawlers\n"
                            + "18. Train Scouts\n"
                            + "Attack Enemy Village:\n"
                            + "19. Print enemy villages\n"
                            + "20. Attack enemy village\n"
                            + "Next player:\n"
                            + "21. Next player\n"
                            + "> "
                    );

                    try {
                        option = Integer.parseInt(scanner.nextLine());

                        if (option < 1 || option > 21) {
                            System.out.println("Invalid: input must be between 1 and 21 inclusive");
                            repeat = true;
                        }
                    } catch (Exception exception) {
                        System.out.println("Invalid: input must be a number");
                        repeat = true;
                    }
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
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                    case 17:
                        break;
                    case 18:
                        break;
                    case 19:
                        break;
                    case 20:
                        break;
                    case 21:
                        nextPlayer = true;
                        break;
                }

                // Train / build commands

                // Attack commands

            }
        }
    }
}
