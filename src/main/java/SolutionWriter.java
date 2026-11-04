import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SolutionWriter {
    private final ChessBoard chessBoard;
    private final Knight knight;
    private final PrintWriter outputFile;
    private final boolean consoleOutput;

    public SolutionWriter(ChessBoard chessBoard, String outputPath)
            throws IOException {

        this.chessBoard = chessBoard;
        this.knight = chessBoard.getKnight();
        this.consoleOutput = outputPath == null;

        if (outputPath == null) {
            this.outputFile = new PrintWriter(System.out, true);
        } else {
            Path path = Path.of(outputPath);

            if (Files.exists(path)) {
                throw new IOException(
                        "El archivo de salida ya existe: " + outputPath
                );
            }

            Files.createFile(path);
            this.outputFile = new PrintWriter(outputPath);
        }
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
        if (!consoleOutput) {
            outputFile.close();
        } else {
            outputFile.flush();
        }
    }

    public void showSolution(boolean generateMode) throws IOException {
        if(!generateMode) showList();
        else    showMatrix();
        if(outputFile.checkError()) {
            throw new IOException("Error al escribir la salida");
        }
    }
}
