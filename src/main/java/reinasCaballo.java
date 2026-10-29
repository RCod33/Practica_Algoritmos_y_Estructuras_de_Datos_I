import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class reinasCaballo {

    static void main(String[] args) {

        boolean traceMode = false;
        boolean showHelp = false;
        boolean generateMode = false;

        String inputFile;

        String inputPath = null;
        String outputPath = null;

        ChessBoard chessBoard;
        SolutionWriter solutionWriter;
        Solver solver;

        for (String arg : args) {
            switch (arg) {
                case "-t":
                    traceMode = true;
                    break;

                case "-h":
                    showHelp = true;
                    break;

                case "-g":
                    generateMode = true;
                    break;
                default:
                    if (arg.startsWith("-")) {
                        System.out.println("Opcion desconocida, por favor utiliza -h para ayuda");
                        return;
                    }

                    if (inputPath == null)
                        inputPath = arg;
                    else if (outputPath == null)
                        outputPath = arg;
                    else
                        throw new IllegalArgumentException("Argumento extra");
                    break;
            }
        }

        if (showHelp) {
            System.out.println(
                    "SINTAXIS:  reinasCaballo  [-t][-h]  [-g]  [fichero  entrada]  [fichero  salida]\n" +
                            "    -t                  Traza cada paso\n" +
                            "    -h                  Muestra esta ayuda\n" +
                            "    -g                  Modo salida gráfica\n" +
                            "    [fichero entrada]   Posición del caballo (filacolumna)\n" +
                            "    [fichero salida]    Posiciones del caballo y de las reinas, o tablero gráfico"
            );
            return;
        }

        if (inputPath == null) throw new NullPointerException("No existe archivo de entrada");
        if (outputPath == null) throw new NullPointerException("No existe archivo de salida");

        try {
            inputFile = Files.readString(Path.of(inputPath));
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
                return;
        }

        try {
            chessBoard = new ChessBoard(inputFile);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        solver = new Solver(chessBoard, traceMode);
        solver.solveBoard(1, false);


        if (generateMode) {
            try {
                solutionWriter = new SolutionWriter(chessBoard, outputPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            solutionWriter.showBoard();
            solutionWriter.close();
        }
    }
}
