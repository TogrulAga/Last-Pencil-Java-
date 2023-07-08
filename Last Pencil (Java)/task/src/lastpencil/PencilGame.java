package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class PencilGame {
    private final Scanner scanner = new Scanner(System.in);
    private int remainingPencils;
    private final String[] players = new String[] {"John", "Jack"};
    private int playerIndex;
    public PencilGame() {
        System.out.println("How many pencils would you like to use:");
        while (true) {
            String countString = scanner.nextLine();
            if (countString.matches("\\d+") && !countString.isBlank() && !countString.isEmpty()) {
                remainingPencils = Integer.parseInt(countString);

                if (remainingPencils > 0) {
                    break;
                } else {
                    System.out.println("The number of pencils should be positive");
                }
            } else {
                System.out.println("The number of pencils should be numeric");
            }
        }

        System.out.println("Who will be the first (John, Jack):");
        while (true) {
            String name = scanner.next();
            if (!name.equals("John") && !name.equals("Jack")) {
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            }
            playerIndex = name.equals("John") ? 0 : 1;
            break;
        }
    }

    public void play() {
        System.out.println("|".repeat(remainingPencils));

        while (remainingPencils > 0) {
            System.out.printf("%s's turn!%n", players[playerIndex]);

            int count = playerIndex == 0 ? playerMoves() : botMoves();

            remainingPencils -= count;

            if (remainingPencils == 0) {
                System.out.printf("%s won!%n", players[(playerIndex + 1) % 2]);
                return;
            }

            printPencils(remainingPencils);
            playerIndex = (playerIndex + 1) % 2;
        }
    }

    private void printPencils(int count) {
        System.out.println("|".repeat(count));
    }

    private int playerMoves() {
        int count;

        while (true) {
            String countString = scanner.next();
            if (countString.matches("\\d+")) {
                count = Integer.parseInt(countString);

                if (count <= 3 && count >= 1) {
                    if (remainingPencils < count) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            } else {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }

        return count;
    }

    private int botMoves() {
        int lastCount = remainingPencils;

        while (true) {
            if (lastCount - 4 > 1) {
                lastCount -= 4;
                continue;
            }
            break;
        }

        switch (lastCount) {
            case 2 -> {
                System.out.println(1);
                return 1;
            }
            case 3 -> {
                System.out.println(2);
                return 2;
            }
            case 4 -> {
                System.out.println(3);
                return 3;
            }
            case 5 -> {
                int number = new Random().nextInt(1, 3);
                System.out.println(number);
                return number;
            }
        }

        System.out.println(1);
        return 1;
    }
}
