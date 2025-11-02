public class N_Queens {
    static final int N = 8;

    // Function to print the board
    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {
        // Check left side of current row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower-left diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve N-Queens using backtracking
    static boolean solveNQueens(int[][] board, int col) {
        // Base case: All queens placed
        if (col >= N)
            return true;

        // Try placing this queen in all rows of current column
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place queen

                // Recur for next column
                if (solveNQueens(board, col + 1))
                    return true;

                // Backtrack if not possible
                board[i][col] = 0;
            }
        }

        return false; // No placement possible in this column
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];

        // Place first queen manually at (0, 0)
        board[0][0] = 1;

        // Start solving from the next column
        if (solveNQueens(board, 1)) {
            System.out.println("Solution found:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}
