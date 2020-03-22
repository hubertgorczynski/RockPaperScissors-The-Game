package RockPaperScissors;

import java.util.Scanner;

public class RoundHandler {
    private String playerMove;
    private String computerMove;
    Conditions stateOfGame;

    public Conditions play(String difficultyChose) {
        Scanner scan = new Scanner(System.in);
        String whatToDo = askForPlayerMove();

        if (whatToDo.equals("x")) {
            System.out.println(Statements.areYouSureExit);
            if (scan.next().equals("x")) {
                return Conditions.EXIT_GAME;
            }
        } else if (whatToDo.equals("n")) {
            System.out.println(Statements.areYouSureNewGame);
            if (scan.next().equals("n")) {
                return Conditions.NEW_GAME;
            }
        } else {
            setHumanMoves(whatToDo);
            setDifficultyLevel(difficultyChose); // set computer moves
            return stateOfGame = compareMoves();
        }
        return Conditions.INVALID_KEY;
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

    private void setDifficultyLevel(String difficultyChose) {
        if (difficultyChose.equals("s")) {
            setComputerMovesStandard();
        }
        if (difficultyChose.equals("h")) {
            setComputerMovesHarder();
        }
    }

    private Conditions compareMoves() {
        System.out.println("You choose: " + playerMove + " vs Computer choose: " + computerMove);
        if (playerMove.equals("rock") && computerMove.equals("scissors")
                || playerMove.equals("paper") && computerMove.equals("rock")
                || playerMove.equals("scissors") && computerMove.equals("paper")) {
            System.out.println("Player wins!");
            return Conditions.HUMAN_WIN;
        } else if (playerMove.equals("rock") && computerMove.equals("paper")
                || playerMove.equals("paper") && computerMove.equals("scissors")
                || playerMove.equals("scissors") && computerMove.equals("rock")) {
            System.out.println("Computer wins!");
            return Conditions.COMPUTER_WIN;
        } else {
            System.out.println("We have a tie!");
            return Conditions.TIE;
        }
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
}



