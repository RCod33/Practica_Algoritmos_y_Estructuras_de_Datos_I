public class Solver {
    private final int[] board;
    private final Knight knight;
    private final int last ;
    private final boolean traceMode;

    public Solver(ChessBoard chessBoard, boolean traceMode) {
        this.board = chessBoard.getBoard();
        this.knight = chessBoard.getKnight();
        this.last = (this.knight.getCol() == 8) ? 7 : 8;
        this.traceMode = traceMode;
    }

    private boolean knightIsAttacking(int col, int row) {
        int diffCol = Math.abs(col - knight.getCol());
        int diffRow = Math.abs(row - knight.getRow());
        boolean isAttacked = (diffCol * diffRow == 2);

        if(isAttacked)
            tracer(row, col, "Descartada: queda a un salto de caballo del caballo");
        else if (col == knight.getCol() || row == knight.getRow() || (diffCol == diffRow))
            tracer(row, col, "Descartada: comparte fila o diagonal con el caballo");

        return col == knight.getCol() || row == knight.getRow() || (diffCol == diffRow) || isAttacked;
    }

    private boolean validCols(int colPivot, int row) {
        for (int col = 1; col < colPivot; ++col) {
            if(col == knight.getCol()) continue;
            if (board[col] == row ) {
                tracer(row, colPivot, "Descartada: misma fila que la reina de " + row +  col);
                return false;
            } else if (Math.abs(col - colPivot) == Math.abs(board[col] - row)) {
                tracer(row, colPivot, "Descartada: misma diagonal que la reina de " + board[col] + col);
                return false;
            }
        }

        return true;

    }

    private void tracer (int row, int col, String message) {
        if(!traceMode) return;

        char c = (char) ('a' + (col - 1));

        System.out.printf("[%d][%s]: %s\n", row, c, message);

    }

    public boolean solveBoard(int col, boolean finished) {
        if (finished) {
            tracer(board[col-1], col-1, "SOLUCIÓN COMPLETA: 7 REINAS COLOCADAS");
            return true;
        }

        if (col == knight.getCol()) {
            tracer(-1, col, "Columna del caballo: no se coloca reina, paso a la columna siguiente");
            return solveBoard(col + 1, false);
        }

        for (int row = 1; row <= 8; ++row) {
            if(knightIsAttacking(col, row)) {continue;}
            if (validCols(col, row)) {
                tracer(row, col, "Reina colocada, paso a la columna siguiente");
                board[col] = row;
                finished = solveBoard(col + 1, col == last);
                if (finished)
                    return true;
                else {
                    tracer(row, col, "No se encontro una solucion válida para esta posicion: siguiente fila");
                    board[col] = 0;
                }
            }
        }

        tracer(-1, col, "Sin filas válidas en esta columna: vuelta a la columna anterior");
        return false;
    }
}
