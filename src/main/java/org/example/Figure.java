package org.example;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private final int x;
    private final int y;
    private final Board board;

    public possibleMoves(int x, int y, Board board) {

    }

    public Figure(int x, int y, Board board) {
        board.checkPositionCollision(x, y);
        this.x = x;
        this.y = y;
        this.board = board;
        board.placeFigure(x,y);
    }

    public List<Position> getPossibleMoves(){

        List<Position> possibleMoves = new ArrayList<>();


    }

}
