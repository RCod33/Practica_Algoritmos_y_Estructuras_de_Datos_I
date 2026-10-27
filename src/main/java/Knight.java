class Knight {
    private final int row;
    private final int col;

    public Knight(String s) {
        s = s.toLowerCase();

        if(s.length() != 2)
            throw new IllegalArgumentException("Posición inválida: " + s);

        row = s.charAt(0) - '0';
        col = s.charAt(1) - 96;

        if (!(row == 1 || row == 8 || col == 1 || col == 8) && (row >= 1 && row <= 8 && col >= 1 && col <= 8)) {
            throw new IllegalArgumentException("Posición inválida: " + s);
        }
    }



    public int getRow() { return row; }

    public int getCol() {
        return col;
    }
}