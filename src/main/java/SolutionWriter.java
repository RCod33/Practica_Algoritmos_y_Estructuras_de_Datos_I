import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SolutionWriter {
    private final ChessBoard chessBoard;
    private final Knight knight;
    private final PrintWriter outputFile;

    public SolutionWriter(ChessBoard chessBoard, String outputPath)
            throws IOException {

        this.chessBoard = chessBoard;
        this.knight = chessBoard.getKnight();

        Path path = Path.of(outputPath);

        if (Files.exists(path)) {
            throw new IOException("Oooops el archivo de salida ya existe: " + outputPath);
        }

        Files.createFile(path);
        this.outputFile = new PrintWriter(outputPath);
    }

    private void showMatrix() {
        outputFile.println("   +---+---+---+---+---+---+---+---+");
        for (int i = 8; i >= 1; --i) {
            outputFile.print(i + "  | ");

            for (int j = 1; j <= 8; ++j) {
                if (chessBoard.getRowOfQueen(j) == i) {
                    outputFile.print("R");
                } else if (i == knight.getRow() && j == knight.getCol()) {
                    outputFile.print("C");
                } else {
                    outputFile.print(" ");
                }
                outputFile.print(" | ");
            }
            outputFile.println();
            outputFile.println("   +---+---+---+---+---+---+---+---+");
        }
        outputFile.println("     a   b   c   d   e   f   g   h\n");
    }

    private void showList() {

        outputFile.print(convertToChessPos(knight.getCol(), knight.getRow()) + " ");

        for(int i = 1; i<=8; ++i) {
            if(chessBoard.getRowOfQueen(i) != 0)
                outputFile.print(convertToChessPos(i, chessBoard.getRowOfQueen(i)) + " ");
        }

        outputFile.println();
    }

    private String convertToChessPos(int col, int row) {
        char letterCol = (char) ('a' + (col - 1));

        return String.valueOf(row) + letterCol;
    }

    public void close() {
        outputFile.close();
    }

    public void showBoard(boolean generateMode) {
        if(!generateMode) showList();
        else {
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
}
