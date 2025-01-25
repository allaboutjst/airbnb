class ConnectFour {
    int row;
    int col;
    char firstPlayer;

    public ConnectFour(int row, int col, char firstPlayer) {
        this.row = row;
        this.col = col;
        this.firstPlayer = firstPlayer;
    }

    public void printBoard(char[][] board) {
        for (int i = 0; i < row; i++) {
            System.out.print("|");
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    public boolean checkWin(char[][] board, char player) {
        // Check horizontal
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 3; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player
                        && board[i][j + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int i = 0; i < row - 3; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player
                        && board[i + 3][j] == player) {
                    return true;
                }
            }
        }

        // Check diagonal
        for (int i = 0; i < row - 3; i++) {
            for (int j = 0; j < col - 3; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player
                        && board[i + 3][j + 3] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < row - 3; i++) {
            for (int j = 3; j < col; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player && board[i + 2][j - 2] == player
                        && board[i + 3][j - 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public void playGame(int[] moves) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = ' ';
            }
        }

        char currentPlayer = firstPlayer;
        
        for (int i = 0; i < moves.length; i++) {
            int colNum = moves[i];
            if (colNum < 0 || colNum >= col || board[0][colNum] != ' ') {
                System.out.println("Invalid move");
                continue;
            }

            for (int j = row - 1; j >= 0; j--) {
                if (board[j][colNum] == ' ') {
                    board[j][colNum] = currentPlayer;
                    break;
                }
            }

            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (i == row * col) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
    }

    public static void main(String[] args) {
        int[] moves = {0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5};
        ConnectFour game = new ConnectFour(6, 7, 'X');
        game.playGame(moves);
    }
}
