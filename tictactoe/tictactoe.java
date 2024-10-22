import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X'; // Player X starts
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-'; // Initialize the board with dashes
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) || 
                checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return checkRowCol(board[0][0], board[1][1], board[2][2]) || 
               checkRowCol(board[0][2], board[1][1], board[2][0]);
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-' && c1 == c2 && c1 == c3);
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        
        while (true) {
            game.printBoard();
            System.out.println("Current player: " + game.currentPlayer);
            System.out.print("Enter row and column (0, 1, or 2) to place your mark: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            game.placeMark(row, col);
            
            if (game.checkForWin()) {
                game.printBoard();
                System.out.println("Player " + game.currentPlayer + " wins!");
                break;
            }
            
            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("The game is a draw!");
                break;
            }
            
            game.changePlayer();
        }
        scanner.close();
    }
}