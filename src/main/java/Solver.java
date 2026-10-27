public class Solver {
    private final ChessBoard chessBoard;
    int[] board;
    Knight knight;

    public Solver(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.board = chessBoard.getBoard();
        this.knight = chessBoard.getKnight();
    }

    private boolean isInvalidSquare(int col, int row) {
        int diffCol = Math.abs(col - knight.getCol());
        int diffRow = Math.abs(row - knight.getRow());
        boolean isAttacked = (diffCol * diffRow == 2);
        return col == knight.getCol() || row == knight.getRow() || (diffCol == diffRow) || isAttacked;
    }

    public void solveBoard(int col, int row) {
        if (col > 8 || row > 8) return;

        int k_col = knight.getCol();
        boolean f = isInvalidSquare(col, row);

        if (!f) {
            for (int i = 1; i < col; ++i) {
                if (i == k_col) continue;
                if (board[i] == row || Math.abs(i - col) == Math.abs(board[i] - row)) {
                    f = true;
                    break;
                }
            }
        }

        int last = (k_col == 8) ? 7 : 8;

        if (!f) {
            board[col] = row;
            solveBoard(col + 1, 1);
            if (board[last] == 0) {
                board[col] = 0;
            }

        } else if (col == k_col) {
            solveBoard(col + 1, 1);
            return;
        }
        if (board[last] == 0) {
            solveBoard(col, row + 1);
        }

    }
}
