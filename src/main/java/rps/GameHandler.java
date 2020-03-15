package rps;

import java.util.Scanner;

public class GameHandler {

    int roundsToWin;
    String playerName;
    String difficultyChose;

    public void start() {
        RoundHandler roundHandler = new RoundHandler();

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

        roundHandler.play(playerName, roundsToWin, difficultyChose);
        roundHandler.displayFinalScores(playerName);
    }

    private int askForRoundsToWin() {
        Scanner roundScanner = new Scanner(System.in);
        roundsToWin = roundScanner.nextInt();
        while (roundsToWin <= 0) {
            System.out.println("Invalid number. Please enter number greater than zero.");
            Scanner numberOfGamesScanner = new Scanner(System.in);
            roundsToWin = numberOfGamesScanner.nextInt();
        }
        return roundsToWin;
    }

    private String askForDifficultyLevel() {
        Scanner difficultyScanner = new Scanner(System.in);
        difficultyChose = difficultyScanner.nextLine();
        while (!difficultyChose.equals("s") && !difficultyChose.equals("h")) {
            System.out.println("Invalid letter. Please choose \"s\" for standard level or \"h\" for hard level.");
            Scanner levelScanner = new Scanner(System.in);
            difficultyChose = levelScanner.nextLine();
        }
        return difficultyChose;
    }
}



