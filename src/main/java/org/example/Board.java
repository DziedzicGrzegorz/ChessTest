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

        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        this.size = size;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public void checkPositionCollision(int x,int y){
        if(x < 0 || x >= this.size || y < 0 || y >= this.size){
            throw new IllegalArgumentException("Position is out of bounds");
        }
        if (this.board[x][y] > 0) {
            throw new IllegalArgumentException("Position is already occupied");
        }

    }


public void printBoard() {
    for (int i = 0; i < this.size; i++) {
        for (int j = 0; j < this.size; j++) {
            if (this.board[i][j] == 1) {
                System.out.print("[f] ");
            } else if (this.board[i][j] == 2) {
                System.out.print("[b] ");
            } else {
                System.out.print("[.] ");
            }
        }
        System.out.println();
    }
}

    public void placeFigure(int x, int y) {
        this.board[x][y] = 1;
    }

    public void placeBlock(int x, int y) {
        checkPositionCollision(x, y);
        this.board[x][y] = 2;
    }

    public boolean isOccupied(int x, int y) {
        if(x < 0 || x >= this.size || y < 0 || y >= this.size){
            return false;
        }
        return this.board[x][y] != 0;
    }

    public boolean isFigure(int x, int y) {
        if(x < 0 || x >= this.size || y < 0 || y >= this.size){
            return false;
        }
        return this.board[x][y] == 1;
    }

    public boolean isBlock(int x, int y) {
        if(x < 0 || x >= this.size || y < 0 || y >= this.size){
            return false;
        }
        return this.board[x][y] == 2;
    }

    public int getSize() {
        return size;
    }
}
