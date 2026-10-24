import java.util.Scanner;

public class reinasCaballo {

    static int[] array = new int[9];

    public static void n_queens(int col, int row, int k_col, int k_row) {
        if (col > 8 || row > 8) return;

        int diffCol = Math.abs(col - k_col);
        int diffRow = Math.abs(row - k_row);
        boolean isAttacked = (diffCol * diffRow == 2);
        boolean f = !(col == k_col || row == k_row || (Math.abs(k_col - col) == Math.abs(k_row - row))) && !isAttacked;

        if (f) {
            for (int i = 1; i < col; ++i) {
                if (i == k_col) continue;              // ahí no hay dama
                if (array[i] == row || Math.abs(i - col) == Math.abs(array[i] - row)) {
                    f = false;
                    break;
                }
            }
        }

        int last = (k_col == 8) ? 7 : 8;

        if (f) {
            array[col] = row;
            n_queens(col + 1, 1, k_col, k_row);
            if(array[last] == 0) {
                array[col] = 0;
            }

        }
        if (array[last] == 0) {
            n_queens(col, row + 1, k_col, k_row);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int k_row = s.charAt(0) - '0';
        int k_col = s.charAt(1) - 96;

        n_queens(1,1, k_col, k_row);

        for (int i = 1; i < 9; ++i)
            System.out.println(array[i] + " ");

        scanner.close();
    }
}