import java.util.Scanner;

public class reinasCaballo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        ChessBoard chessBoard;
        SolutionWriter solutionWriter;
        Solver solver;

        try {
            chessBoard = new ChessBoard(s);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        solutionWriter = new SolutionWriter(chessBoard);
        solver = new Solver(chessBoard);

        solver.solveBoard(1, 1);

        solutionWriter.showBoard();

        scanner.close();
    }
}