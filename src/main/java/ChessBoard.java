public class ChessBoard {
    private final int[] board = new int[9];
    private final Knight knight;
    private final Tracer tracer;

    public ChessBoard(String knight, Tracer tracer) {
        this.knight = new Knight(knight);
        this.tracer = tracer;
    }

    public Knight getKnight() {
        return knight;
    }

    public void putQueen(int col, int row) {
        board[col] = row;
    }

    public void quitQueen(int col) {
        board[col] = 0;
    }

    public int getRowOfQueen(int col) {
        return board[col];
    }

    public boolean isSafeSquare(int col, int row) {
        return isSafeFromKnight(row, col) && isSafeFromQueens(row, col);
    }

    private boolean isSafeFromKnight(int row, int col) {
        int diffCol = Math.abs(col - knight.getCol());
        int diffRow = Math.abs(row - knight.getRow());

        // Ataque del caballo (salto en L)
        if (diffCol * diffRow == 2) {
            tracer.knightJump(row, col);
            return false;
        }

        // La reina atacaría al caballo (columna, fila o diagonal)
        if (col == knight.getCol() || row == knight.getRow() || diffCol == diffRow) {
            tracer.knightLine(row, col);
            return false;
        }

        return true;
    }

    private boolean isSafeFromQueens(int row, int col) {
        for (int colTemp = 1; colTemp < col; ++colTemp) {
            int queenRow = board[colTemp];
            if (queenRow == 0) continue;

            if (queenRow == row) {
                tracer.sameRow(row, col, queenRow, colTemp);
                return false;
            }
            if (Math.abs(colTemp - col) == Math.abs(queenRow - row)) {
                tracer.sameDiagonal(row, col, queenRow, colTemp);
                return false;
            }
        }

        return true;
    }
}