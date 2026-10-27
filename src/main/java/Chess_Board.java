import java.util.Scanner;

public class Chess_Board {
    private int[] board = new int[9];
    private Knight knight;

    public Chess_Board(String knight) {
        this.knight = new Knight(knight);
    }

    private boolean isValidSquare(int col, int row) {
        int diffCol = Math.abs(col - knight.get_col());
        int diffRow = Math.abs(row - knight.get_row());
        boolean isAttacked = (diffCol * diffRow == 2);
        return col == knight.get_col() || row == knight.get_row() || (diffCol == diffRow) || isAttacked;
    }

    public void solver(int col, int row) {
        if (col > 8 || row > 8) return;

        int k_col = knight.get_col();
        boolean f = isValidSquare(col, row);

        if (!f) {
            for (int i = 1; i < col; ++i) {
                if (i == k_col) continue;
                if (board[i] == row || Math.abs(i - col) == Math.abs(board[i] - row)) {
                    f = true;
                    break;
                }
            }
        }

        int last = (k_col == 8) ? 7 : 8;

        if (!f) {
            board[col] = row;
            solver(col + 1, 1);
            if (board[last] == 0) {
                board[col] = 0;
            }

        } else if (col == k_col) {
            solver(col + 1, 1);
            return;
        }
        if (board[last] == 0) {
            solver(col, row + 1);
        }

    }

    private void showMatrix() {
        System.out.println("   +---+---+---+---+---+---+---+---+");
        for (int i = 8; i >= 1; --i) {
            System.out.print(i + "  | ");

            for (int j = 1; j <= 8; ++j) {
                if (board[j] == i) {
                    System.out.print("R");
                } else if (i == knight.get_row() && j == knight.get_col()) {
                    System.out.print("C");
                } else {
                    System.out.print(" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("   +---+---+---+---+---+---+---+---+");
        }
        System.out.println("     a   b   c   d   e   f   g   h\n");
    }

    private void showList() {

        System.out.print("\n" + convertToChessPos(knight.get_col(), knight.get_row()) + " ");

        for(int i = 1; i<=8; ++i) {
            if(board[i] != 0)
                System.out.print(convertToChessPos(i, board[i]) + " ");
        }

        System.out.println();
    }

    private String convertToChessPos(int col, int row) {
        char letterCol = (char) ('a' + (col - 1));

        return String.valueOf(row) + letterCol;
    }

    public void showBoard () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una opcion:");
        System.out.println("1. Mostrar matriz");
        System.out.println("2. Mostrar lista");
        System.out.print("Ingrese el numero de su eleccion: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1: showMatrix(); break;
            case 2: showList(); break;
            default: System.out.println("Eleccion no valida");
        }
    }



}
