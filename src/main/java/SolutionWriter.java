import java.util.Scanner;

public class SolutionWriter {
    private final ChessBoard chessBoard;
    int[] board;
    Knight knight;

    public SolutionWriter(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.board = chessBoard.getBoard();
        this.knight = chessBoard.getKnight();
    }

    private void showMatrix() {
        System.out.println("   +---+---+---+---+---+---+---+---+");
        for (int i = 8; i >= 1; --i) {
            System.out.print(i + "  | ");

            for (int j = 1; j <= 8; ++j) {
                if (board[j] == i) {
                    System.out.print("R");
                } else if (i == knight.getRow() && j == knight.getCol()) {
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

        System.out.print("\n" + convertToChessPos(knight.getCol(), knight.getRow()) + " ");

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

    public void showBoard() {
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
