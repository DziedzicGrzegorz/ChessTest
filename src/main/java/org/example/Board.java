package org.example;

public class Board {
    private final int size;
    private final BoardEnum[][] board;

    public Board(int size) {

        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        this.size = size;
        this.board = new BoardEnum[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = BoardEnum.EMPTY;
            }
        }
    }

    public void checkOutOfBounds(int x, int y) {
        if (x < 0 || x >= this.size || y < 0 || y >= this.size) {
            throw new IllegalArgumentException("Position" + "x" + " " + "y" + " is out of bounds");
        }
    }

    public void checkPositionCollision(int x, int y) {
        checkOutOfBounds(x, y);

        if (this.board[x][y] != BoardEnum.EMPTY) {
            throw new IllegalArgumentException("Position is already occupied");
        }
    }

    public void placeFigure(int x, int y) {
        checkOutOfBounds(x, y);
        this.board[x][y] = BoardEnum.FIGURE;
    }

    public void placeBlock(int x, int y) {
        checkPositionCollision(x, y);
        this.board[x][y] = BoardEnum.BLOCK;
    }

    public boolean isOccupied(int x, int y) {
        checkOutOfBounds(x, y);
        return this.board[x][y] != BoardEnum.EMPTY;
    }

    public boolean isFigure(int x, int y) {
        checkOutOfBounds(x, y);
        return this.board[x][y] == BoardEnum.FIGURE;
    }

    public boolean isBlock(int x, int y) {
        checkOutOfBounds(x, y);
        return this.board[x][y] == BoardEnum.BLOCK;
    }

    public int getSize() {
        return size;
    }
}
