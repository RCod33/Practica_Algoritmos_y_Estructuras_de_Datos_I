import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class reinasCaballo {

    public static void main(String[] args) {

        ChessBoard chessBoard;
        Tracer tracer;
        Arguments arguments;

        String inputFile;

        try {
            arguments = processArguments(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        tracer = new Tracer(arguments.traceMode);

       if(arguments.showHelp) {
           showHelp();
           return;
       }

        if (arguments.inputPath == null) {
            System.err.println("Error: falta el fichero de entrada.");
            return;
        }

        try {
            inputFile = Files.readString(Path.of(arguments.inputPath)).strip();
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

        try {
            solveAndWrite(
                    chessBoard,
                    tracer,
                    arguments.outputPath,
                    arguments.generateMode
            );
        } catch (IOException e) {
            System.err.println("Error escribiendo el resultado: " + e.getMessage());
        }

    }

    private static void showHelp() {
        System.out.println(
                "SINTAXIS:  reinasCaballo  [-t][-h]  [-g]  [fichero  entrada]  [fichero  salida]\n" +
                        "    -t                  Traza cada paso\n" +
                        "    -h                  Muestra esta ayuda\n" +
                        "    -g                  Modo salida gráfica\n" +
                        "    [fichero entrada]   Posición del caballo (filacolumna)\n" +
                        "    [fichero salida]    Posiciones del caballo y de las reinas, o tablero gráfico"
        );
    }

    private static void solveAndWrite(
            ChessBoard chessBoard,
            Tracer tracer,
            String outputPath,
            boolean generateMode
    ) throws IOException {

        Solver solver = new Solver(chessBoard, tracer);

        if (!solver.solveBoard(1, false)) {
            System.err.println(
                    "No existe solución para la posición " + chessBoard
            );
            return;
        }

        SolutionWriter solutionWriter = new SolutionWriter(chessBoard, outputPath);

        try {
            solutionWriter.showBoard(generateMode);
        } finally {
            solutionWriter.close();
        }
    }

    private static Arguments processArguments(String[] args) {

        Arguments arguments = new Arguments();

        for (String arg : args) {
            switch (arg) {
                case "-t":
                     arguments.traceMode = true;
                    break;

                case "-h":
                    arguments.showHelp = true;
                    break;

                case "-g":
                    arguments.generateMode = true;
                    break;
                default:
                    if (arg.startsWith("-")) {
                        throw new IllegalArgumentException(
                                "Opción desconocida: " + arg + ". Utiliza -h para ayuda."
                        );
                    }

                    if (arguments.inputPath == null)
                        arguments.inputPath = arg;
                    else if (arguments.outputPath == null)
                        arguments.outputPath = arg;
                    else
                        throw new IllegalArgumentException("Argumento extra");
                    break;
            }
        }
        return arguments;
    }

    private static class Arguments {
        boolean traceMode = false;
        boolean showHelp = false;
        boolean generateMode = false;

        String inputPath = null;
        String outputPath = null;

    }

}
