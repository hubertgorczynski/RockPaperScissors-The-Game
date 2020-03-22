package RockPaperScissors;

import java.util.Scanner;

public class GameHandler {
    int rounds = 1;
    int numberOfHumanVictories;
    int numberOfComputerVictories;
    int numberOfTies;
    int roundsToWin;
    String playerName;
    boolean end = false;
    String difficultyChose;

    public void start() {

        while (!end) {
            boolean endRound = false;
            numberOfHumanVictories = 0;
            numberOfComputerVictories = 0;
            numberOfTies = 0;
            rounds = 1;

            System.out.println("\nWELCOME IN ROCK-PAPER-SCISSORS THE GAME!!! \nPlease enter Your name below:");
            Scanner nameScanner = new Scanner(System.in);
            playerName = nameScanner.nextLine();

            System.out.println("\nPlease set a number of rounds to win:");
            askForRoundsToWin();

            System.out.println("\nPlease choose a difficulty level:"
                    + "\nStandard (press \"s\")"
                    + "\nHard (press \"h\")");
            askForDifficultyLevel();
            if (difficultyChose.equals("s")) {
                System.out.println("\nYou choose standard difficulty level.");
            }
            if (difficultyChose.equals("h")) {
                System.out.println("\nYou choose hard difficulty level. Good luck!");
            }

            RoundHandler roundHandler = new RoundHandler();

            while (!endRound && numberOfHumanVictories < roundsToWin && numberOfComputerVictories < roundsToWin) {
                System.out.println("\nRound: " + rounds);

                Conditions stateOfGame = roundHandler.play(difficultyChose);

                if (stateOfGame.equals(Conditions.EXIT_GAME)) {
                    end = true;
                    endRound = true;
                } else if ((stateOfGame.equals(Conditions.NEW_GAME))) {
                    endRound = true;
                } else if ((stateOfGame.equals(Conditions.HUMAN_WIN))) {
                    numberOfHumanVictories++;
                    rounds++;
                    displayActualScores();
                } else if ((stateOfGame.equals(Conditions.COMPUTER_WIN))) {
                    numberOfComputerVictories++;
                    rounds++;
                    displayActualScores();
                } else if (stateOfGame.equals(Conditions.TIE)) {
                    numberOfTies++;
                    rounds++;
                    displayActualScores();
                } else if (stateOfGame.equals(Conditions.INVALID_KEY)) {
                    System.out.println("Please enter correct letter or number!");
                }
            }
            displayFinalScores();
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
        while (!difficultyChose.equals("s") && !difficultyChose.equals("h")) {
            System.out.println("Invalid letter. Please choose \"s\" for standard level or \"h\" for hard level.");
            Scanner levelScanner = new Scanner(System.in);
            difficultyChose = levelScanner.nextLine();
        }
    }

    private void displayActualScores() {
        System.out.println("\n|Actual scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories);
    }

    public void displayFinalScores() {
        System.out.println("\n|Final scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories + "\n Number of ties: " + numberOfTies +
                "\n Rounds passed: " + (rounds - 1));
    }
}



