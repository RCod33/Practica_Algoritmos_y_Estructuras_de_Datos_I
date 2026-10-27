class Knight {
    private int row;
    private int col;

    public Knight(String s) {
        row = s.charAt(0) - '0';
        col = s.charAt(1) - 96;

        if (!(row == 1 || row == 8 || col == 1 || col == 8)) {
            throw new IllegalArgumentException("Posición inválida: " + s);
        }
    }



    public int get_row() { return row; }

    public int get_col() {
        return col;
    }
}