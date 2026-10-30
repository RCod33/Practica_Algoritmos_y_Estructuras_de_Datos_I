public class Solver {
    private  ChessBoard chessBoard;
    private final Knight knight;
    private final int last;
    private final Tracer tracer;

    public Solver(ChessBoard chessBoard, Tracer tracer) {
        this.chessBoard = chessBoard;
        this.knight = chessBoard.getKnight();
        this.tracer = tracer;
        this.last = (this.knight.getCol() == 8) ? 7 : 8;
    }

    public boolean solveBoard(int col, boolean finished) {
        if (finished) {
            tracer.solutionComplete( chessBoard.getRowOfQueen(col-1), col-1);
            return true;
        }

        if (col == knight.getCol()) {
            tracer.knightColumn(col);
            return solveBoard(col + 1, false);
        }

        for (int row = 1; row <= 8; ++row) {
            if (chessBoard.isSafeSquare(col, row)) {
                chessBoard.putQueen(col, row);
                finished = solveBoard(col + 1, col == last);
                if (finished)
                    return true;
                else {
                    chessBoard.quitQueen(col);
                }
            }
        }

        tracer.noSolutionForConfig(col);

        return false;
    }
}
