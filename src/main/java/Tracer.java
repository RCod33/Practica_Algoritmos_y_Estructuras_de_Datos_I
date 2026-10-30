public class Tracer {
    private final boolean enabled;

    public Tracer(boolean enabled) {
        this.enabled = enabled;
    }
    // ---- Casillas descartadas (las llama ChessBoard.isSafeSquare) ----

    public void knightJump(int row, int col) {
        print(row, col, "Descartada: queda a un salto de caballo del caballo");
    }

    public void knightLine(int row, int col) {
        print(row, col, "Descartada: comparte fila o diagonal con el caballo");
    }

    public void sameRow(int row, int col, int queenRow, int queenCol) {
        print(row, col, "Descartada: misma fila que la reina de " + pos(queenRow, queenCol));
    }

    public void sameDiagonal(int row, int col, int queenRow, int queenCol) {
        print(row, col, "Descartada: misma diagonal que la reina de " + pos(queenRow, queenCol));
    }

    // ---- Avance y retroceso ----

    public void knightColumn(int col) {
        printCol(col, "Columna del caballo: no se coloca reina, paso a la columna siguiente");
    }

    public void queenPlaced(int row, int col) {
        print(row, col, "Reina colocada, paso a la columna siguiente");
    }

    public void noSolutionForSquare(int row, int col) {
        print(row, col, "No se encontro una solucion válida para esta posicion: siguiente fila");
    }

    public void noSolutionForConfig(int col) {
        printCol(col, "No se encontro una solucion válida para esta configuracion: siguiente fila");
    }

    public void noValidRows(int col) {
        printCol(col, "Sin filas válidas en esta columna: vuelta a la columna anterior");
    }

    public void solutionComplete(int lastRow, int lastCol) {
        print(lastRow, lastCol, "SOLUCIÓN COMPLETA: 7 REINAS COLOCADAS");
    }

    // ---- Auxiliares ----

    private void print(int row, int col, String message) {
        if (!enabled) return;
        System.out.printf("[%d][%s]: %s%n", row, letter(col), message);
    }

    private void printCol(int col, String message) {
        if (!enabled) return;
        System.out.printf("[%s]: %s%n", letter(col), message);
    }

    private static char letter(int col) {
        return (char) ('a' + (col - 1));
    }

    private static String pos(int row, int col) {
        return "" + row + letter(col);
    }
}