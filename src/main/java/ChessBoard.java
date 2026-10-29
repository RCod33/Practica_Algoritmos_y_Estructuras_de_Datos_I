public class ChessBoard {
    private int[] board = new int[9];
    private Knight knight;

    public ChessBoard(String knight) {
        this.knight = new Knight(knight);
    }

    public Knight getKnight() {
        return knight;
    }

    public int[] getBoard() {
        return board;
    }

}
