

public class GenerateAllPossible3x3MagicSquares {
    private static final int SIZE = 3;
    private static final int MAGIC_CONSTANT = 15;

    public static void main(String[] args) {
        int[][] grid = new int[SIZE][SIZE];
        boolean[] used = new boolean[SIZE * SIZE + 1]; // To keep track of used numbers

        generateMagicSquares(grid, used, 0);
    }

    private static void generateMagicSquares(int[][] grid, boolean[] used, int pos) {
        if (pos == SIZE * SIZE) {
            if (isMagicSquare(grid)) {
                printMagicSquare(grid);
            }
            return;
        }

        for (int num = 1; num <= SIZE * SIZE; num++) {
            if (!used[num]) {
                used[num] = true;
                grid[pos / SIZE][pos % SIZE] = num;
                generateMagicSquares(grid, used, pos + 1);
                used[num] = false; // Backtrack
            }
        }
    }

    private static boolean isMagicSquare(int[][] grid) {
        int sum = 0;
        // Check rows
        for (int row = 0; row < SIZE; row++) {
            int rowSum = 0;
            for (int col = 0; col < SIZE; col++) {
                rowSum += grid[row][col];
            }
            if (sum == 0) {
                sum = rowSum;
            } else if (rowSum != sum) {
                return false;
            }
        }

        // Check columns
        for (int col = 0; col < SIZE; col++) {
            int colSum = 0;
            for (int row = 0; row < SIZE; row++) {
                colSum += grid[row][col];
            }
            if (colSum != sum) {
                return false;
            }
        }

        // Check diagonals
        int diagonalSum1 = 0;
        int diagonalSum2 = 0;
        for (int i = 0; i < SIZE; i++) {
            diagonalSum1 += grid[i][i];
            diagonalSum2 += grid[i][SIZE - i - 1];
        }
        if (diagonalSum1 != sum || diagonalSum2 != sum) {
            return false;
        }

        return true;
    }

    private static void printMagicSquare(int[][] grid) {
        System.out.println("Magic Square:");
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
