package org.example;

import java.util.List;

public class Board {
    private final int size;
    private final int [][] board;
    // tab = 0
    //tab[i][j] = 1;
    //while podaj pzyje x i y w ktoam ma byc figura
    //block = 2

    public Board (int size) {
        this.size = size;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public void checkPositionCollision(int x,int y){
        if (this.board[x][y] == 1) {
            throw new IllegalArgumentException("Position is already occupied by a figure");
        }
        if (this.board[x][y] == 2) {
            throw new IllegalArgumentException("Position is already occupied by a block");
        }
        if(x < 0 || x >= this.size || y < 0 || y >= this.size){
            throw new IllegalArgumentException("Position is out of bounds");
        }
    }

    public void placeFigure(int x, int y) {
        this.board[x][y] = 1;
    }

    public void placeBlock(int x, int y) {
        checkPositionCollision(x, y);
        this.board[x][y] = 2;
    }


    public static void main(String[] args) {
        Board board = new Board(8);
        board.placeFigure(0, 0);
        board.placeBlock(1, 1);


    }



}
