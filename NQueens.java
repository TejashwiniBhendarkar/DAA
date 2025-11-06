import java.util.*;

public class NQueens {
    
    // Print the chessboard
    public static void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if a queen can be safely placed at (row, col)
    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower-left diagonal
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower-right diagonal
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Recursive function to solve the N-Queens problem
    public static boolean solve(int[][] board, int row, int n) {
        // Base case: all rows are processed
        if (row == n)
            return true;

        // Skip row if queen already placed
        boolean hasQueen = false;
        for (int col = 0; col < n; col++) {
            if (board[row][col] == 1) {
                hasQueen = true;
                break;
            }
        }
        if (hasQueen)
            return solve(board, row + 1, n);

        // Try placing a queen in each column
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;
                if (solve(board, row + 1, n))
                    return true;
                board[row][col] = 0; // backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Queens (N): ");
        int n = sc.nextInt();

        System.out.print("Enter row for first Queen (0 to " + (n - 1) + "): ");
        int r = sc.nextInt();

        System.out.print("Enter column for first Queen (0 to " + (n - 1) + "): ");
        int c = sc.nextInt();

        int[][] board = new int[n][n];
        board[r][c] = 1;

        System.out.println("\nInitial Board:");
        printBoard(board, n);

        long start = System.nanoTime(); // start timer

        if (!solve(board, 0, n)) {
            System.out.println("No solution found for this fixed placement.");
        } else {
            System.out.println("Final N-Queen Matrix:");
            printBoard(board, n);
        }

        long end = System.nanoTime(); // end timer
        double timeTakenMs = (end - start) / 1_000_000.0;
        System.out.printf("Time taken: %.3f ms%n", timeTakenMs);

        sc.close();
    }
}
