package rps;

import java.util.Scanner;

public class RoundHandler {
    int rounds = 1;
    int numberOfHumanVictories = 0;
    int numberOfComputerVictories = 0;
    int numberOfTies = 0;
    String playerMove;
    String computerMove;

    public void play(String playerName, int roundsToWin, String difficultyChose) {
        while ((numberOfHumanVictories < roundsToWin) && (numberOfComputerVictories < roundsToWin)) {
            String whatToDo;
            Scanner scan = new Scanner(System.in);
            GameHandler gameHandler = new GameHandler();

            whatToDo = askForPlayerMove();

            if (whatToDo.equals("x")) {
                System.out.println(Statements.areYouSureExit);
                if (scan.next().equals("x")) {
                    displayFinalScores(playerName);
                }
            }

            if (whatToDo.equals("n")) {
                System.out.println(Statements.areYouSureNewGame);
                if (scan.next().equals("n")) {
                    gameHandler.start();
                }
            }

            setHumanMoves(whatToDo);
            setDifficultyLevel(difficultyChose);
            compareMoves(playerName, roundsToWin, difficultyChose);
        }
    }

    private String askForPlayerMove() {
        System.out.println(Statements.rules);
        Scanner moveScanner = new Scanner(System.in);
        String playerChoice = moveScanner.next();
        if (playerChoice.equals("1") || playerChoice.equals("2") || playerChoice.equals("3")
                || playerChoice.equals("x") || playerChoice.equals("n")) {
            return playerChoice;
        }
        System.out.println("Invalid key used. Please enter one from these keys: 1, 2, 3, n, x.");
        askForPlayerMove();
        return playerChoice;
    }

    private void compareMoves(String playerName, int roundsToWin, String difficultyChose) {
        System.out.println("\n|Round: " + rounds + "|");
        System.out.println(playerName + " choose: " + playerMove + "\nComputer choose: " + computerMove);
        if (playerMove.equals("rock") && computerMove.equals("scissors")
                || playerMove.equals("paper") && computerMove.equals("rock")
                || playerMove.equals("scissors") && computerMove.equals("paper")) {
            System.out.println(playerName + " wins!");
            numberOfHumanVictories++;
        } else if (playerMove.equals("rock") && computerMove.equals("paper")
                || playerMove.equals("paper") && computerMove.equals("scissors")
                || playerMove.equals("scissors") && computerMove.equals("rock")) {
            System.out.println("Computer wins!");
            numberOfComputerVictories++;
        } else if (playerMove.equals(computerMove)) {
            System.out.println("We have a tie!");
            numberOfTies++;
        }
        rounds++;
        displayActualScores(playerName);
        play(playerName, roundsToWin, difficultyChose);
    }

    private void setHumanMoves(String text) {
        if (text.equals("1")) {
            playerMove = "rock";
        } else if (text.equals("2")) {
            playerMove = "paper";
        } else if (text.equals("3")) {
            playerMove = "scissors";
        }
    }

    public void setDifficultyLevel(String difficultyChose) {
        if (difficultyChose.equals("s")) {
            setComputerMovesStandard();
        }
        if (difficultyChose.equals("h")) {
            setComputerMovesHarder();
        }
    }

    private void setComputerMovesStandard() {
        computerMove = Math.random() < 0.33 ? "paper" : (Math.random() >= 0.33 && Math.random() < 0.66
                ? "scissors" : "rock");
    }

    private void setComputerMovesHarder() {
        if (playerMove.equals("rock")) {
            computerMove = Math.random() <= 0.50 ? "paper" : (Math.random() < 0.25 && Math.random() < 0.25
                    ? "scissors" : "rock");
        } else if (playerMove.equals("paper")) {
            computerMove = Math.random() <= 0.50 ? "scissors" : (Math.random() < 0.25 && Math.random() < 0.25
                    ? "paper" : "rock");
        } else if (playerMove.equals("scissors")) {
            computerMove = Math.random() <= 0.50 ? "rock" : (Math.random() < 0.25 && Math.random() < 0.25
                    ? "scissors" : "scissors");
        }
    }

    public void displayFinalScores(String playerName) {
        System.out.println("\n|Final scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories + "\n Number of ties: " + numberOfTies +
                "\n Rounds passed: " + rounds);
    }

    private void displayActualScores(String playerName) {
        System.out.println("\n|Actual scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories);
    }
}
