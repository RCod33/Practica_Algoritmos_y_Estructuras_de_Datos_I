import java.util.Scanner;

public class reinasCaballo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        Chess_Board chessBoard;

        try {
            chessBoard = new Chess_Board(s);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        chessBoard.solver(1, 1);

        chessBoard.showBoard();

        scanner.close();
    }
}