import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class reinasCaballo {

    public static void main(String[] args) {

        boolean traceMode = false;
        boolean showHelp = false;
        boolean generateMode = false;

        String inputFile;

        String inputPath = null;
        String outputPath = null;

        ChessBoard chessBoard;
        SolutionWriter solutionWriter;
        Solver solver;
        Tracer tracer;

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

        tracer = new Tracer(traceMode);

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

        if (inputPath == null) {
            System.err.println("Error: falta el fichero de entrada. Usa -h para ver la ayuda.");
            return;
        }
        if (outputPath == null)  {
            System.err.println("Error: falta el fichero de salida. Usa -h para ver la ayuda.");
            return;
        }

        try {
            inputFile = Files.readString(Path.of(inputPath)).strip();
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
                return;
        }

        try {
            chessBoard = new ChessBoard(inputFile, tracer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        solver = new Solver(chessBoard, tracer);


        if (!solver.solveBoard(1, false)) {
            System.err.println("No existe solución para la posición " + inputFile);
            return;
        }

        try {
            solutionWriter = new SolutionWriter(chessBoard, outputPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        solutionWriter.showBoard(generateMode);
        solutionWriter.close();

    }
}
