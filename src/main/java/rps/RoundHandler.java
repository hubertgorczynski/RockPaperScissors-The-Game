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
        switch (text) {
            case "1":
                playerMove = "rock";
                break;
            case "2":
                playerMove = "paper";
                break;
            case "3":
                playerMove = "scissors";
                break;
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
        if (Math.random() < 0.33) {
            computerMove = "paper";
        } else {
            if (Math.random() >= 0.33 && Math.random() < 0.66) {
                computerMove = "scissors";
            } else {
                computerMove = "rock";
            }
        }
    }

    private void setComputerMovesHarder() {
        if (playerMove.equals("rock")) {
            if (Math.random() <= 0.50) {
                computerMove = "paper";
            } else {
                if (Math.random() < 0.25) {
                    computerMove = "scissors";
                } else {
                    computerMove = "rock";
                }
            }
        }

        if (playerMove.equals("paper")) {
            if (Math.random() <= 0.50) {
                computerMove = "scissors";
            } else {
                if (Math.random() < 0.25) {
                    computerMove = "rock";
                } else {
                    computerMove = "paper";
                }
            }
        }

        if (playerMove.equals("scissors")) {
            if (Math.random() <= 0.50) {
                computerMove = "rock";
            } else {
                if (Math.random() < 0.25) {
                    computerMove = "paper";
                } else {
                    computerMove = "scissors";
                }
            }
        }
    }

    public void displayFinalScores(String playerName) {
        System.out.println("\n|Final scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories + "\n Number of ties: " + numberOfTies +
                "\n Rounds passed: " + (rounds - 1));
    }

    private void displayActualScores(String playerName) {
        System.out.println("\n|Actual scoreboard|" + "\n " + playerName + " wins: " + numberOfHumanVictories +
                "\n Computer wins: " + numberOfComputerVictories);
    }
}
