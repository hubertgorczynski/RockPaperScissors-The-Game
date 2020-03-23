package RockPaperScissors;

import java.util.Scanner;

public class GameHandler {
    private int numberOfHumanVictories;
    private int numberOfComputerVictories;
    private int numberOfTies;
    private int roundsToWin;
    private String playerName;
    private String difficultyChose;

    public void start() {
        boolean end = false;

        while (!end) {
            boolean endRound = false;
            int rounds = 1;
            numberOfHumanVictories = 0;
            numberOfComputerVictories = 0;
            numberOfTies = 0;

            System.out.println("\nWELCOME IN ROCK-PAPER-SCISSORS THE GAME!!! \nPlease enter Your name below:");
            Scanner nameScanner = new Scanner(System.in);
            playerName = nameScanner.nextLine();

            System.out.println("\nPlease set a number of rounds to win:");
            askForRoundsToWin();

            System.out.println("\nPlease choose a difficulty level:"
                    + "\nEasy (press \"e\")"
                    + "\nStandard (press \"s\")"
                    + "\nHard (press \"h\")");
            askForDifficultyLevel();
            switch (difficultyChose) {
                case "e":
                    System.out.println("\nYou choose easy difficulty level.");
                    break;
                case "s":
                    System.out.println("\nYou choose standard difficulty level.");
                    break;
                case "h":
                    System.out.println("\nYou choose hard difficulty level. Good luck!");
                    break;
            }

            RoundHandler roundHandler = new RoundHandler();

            while (!endRound && numberOfHumanVictories < roundsToWin && numberOfComputerVictories < roundsToWin) {
                System.out.println("\nRound: " + rounds);

                Conditions stateOfGame = roundHandler.play(difficultyChose);

                switch (stateOfGame) {
                    case EXIT_GAME:
                        end = true;
                        endRound = true;
                        break;
                    case NEW_GAME:
                        endRound = true;
                        break;
                    case HUMAN_WIN:
                        numberOfHumanVictories++;
                        rounds++;
                        displayActualScores();
                        break;
                    case COMPUTER_WIN:
                        numberOfComputerVictories++;
                        rounds++;
                        displayActualScores();
                        break;
                    case TIE:
                        numberOfTies++;
                        rounds++;
                        displayActualScores();
                        break;
                    case INVALID_KEY:
                        System.out.println("Invalid key used!");
                        break;
                }
            }
            displayFinalScores(rounds);
        }
    }

    private void askForRoundsToWin() {
        Scanner roundScanner = new Scanner(System.in);
        roundsToWin = roundScanner.nextInt();
        while (roundsToWin <= 0) {
            System.out.println("Invalid number. Please enter number greater than zero.");
            Scanner numberOfGamesScanner = new Scanner(System.in);
            roundsToWin = numberOfGamesScanner.nextInt();
        }
    }

    private void askForDifficultyLevel() {
        Scanner difficultyScanner = new Scanner(System.in);
        difficultyChose = difficultyScanner.nextLine();
        while (!difficultyChose.equals("e") && !difficultyChose.equals("s") && !difficultyChose.equals("h")) {
            System.out.println("Invalid letter. Please choose: \n\"e\" for easy level, \n\"s\" for standard level, or \n\"h\" for hard level.");
            Scanner levelScanner = new Scanner(System.in);
            difficultyChose = levelScanner.nextLine();
        }
    }

    private void displayActualScores() {
        System.out.println("\n|Actual scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories);
    }

    private void displayFinalScores(int rounds) {
        System.out.println("\n|Final scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories + "\n Number of ties: " + numberOfTies +
                "\n Rounds passed: " + (rounds - 1));
    }
}



