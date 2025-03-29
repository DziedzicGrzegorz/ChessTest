package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(2, 2);

        List<Position> possibleMoves = rook.getPossibleMoves();
        for (Position move : possibleMoves) {
            System.out.println("Possible move: (" + move.getX() + ", " + move.getY() + ")");
        }
    }
}