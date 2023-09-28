package src;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private char[][] table;
    private boolean firstPlayer;

    public TicTacToe() {
        table = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        firstPlayer = true;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        display();
        analyzeGameState();
        boolean validMove = false;

        while (!validMove) {
            try {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                validMove = makeMove(x, y);
                if (!validMove) {
                    display();
                    String gameState = analyzeGameState();
                    System.out.println(gameState);
                    if (gameState.equals("O wins") || gameState.equals("X wins") || gameState.equals("Draw")) {
                        break;
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    public void display() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean makeMove(int x, int y) {
        boolean found = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ') {
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                if (table[x][y] == ' ') {
                    if (firstPlayer) {
                        table[x][y] = 'X';
                        firstPlayer = false;
                    } else {
                        table[x][y] = 'O';
                        firstPlayer = true;
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } else {
            return true;
        }
        return false;
    }

    public String analyzeGameState() {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 'X') {
                    xCount++;
                } else if (table[i][j] == 'O') {
                    oCount++;
                }
            }
        }

        int diff = Math.abs(xCount - oCount);
        boolean xWins = checkWin('X');
        boolean oWins = checkWin('O');

        if (xWins && oWins || diff >= 2) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (xCount + oCount == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == player && table[i][1] == player && table[i][2] == player) {
                return true;
            }
            if (table[0][i] == player && table[1][i] == player && table[2][i] == player) {
                return true;
            }
        }

        if (table[0][0] == player && table[1][1] == player && table[2][2] == player) {
            return true;
        }
        if (table[0][2] == player && table[1][1] == player && table[2][0] == player) {
            return true;
        }

        return false;
    }
}