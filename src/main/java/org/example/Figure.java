package org.example;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private final int x;
    private final int y;
    private final Board board;


    public Figure(int x, int y, Board board) {
        board.checkPositionCollision(x, y);
        this.x = x;
        this.y = y;
        this.board = board;
        board.placeFigure(x, y);
    }

    public List<Position> getPossibleMoves() {

        List<Position> possibleMoves = new ArrayList<>();

        checkDirection(possibleMoves, 1, 1);

        checkDirection(possibleMoves, 1, -1);

        checkDirection(possibleMoves, -1, 1);

        checkDirection(possibleMoves, -1, -1);

        return possibleMoves;
    }

    public void checkDirection(List<Position> possibleMoves, int dirX, int dirY) {
        int newX = x + dirX;
        int newY = y + dirY;

        while (isValidPosition(newX, newY)) {
            possibleMoves.add(new Position(newX, newY));

            if (board.isOccupied(newX, newY)) {
                break;
            }
            newX += dirX;
            newY += dirY;
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize();
    }
}
