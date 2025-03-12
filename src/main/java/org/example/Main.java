package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // board 6
        // figura new Figura()
        //setFiguraPo(figura,positioj)

            Board board = new Board(8);
            Figure figure = new Figure(0, 0, board);

        List<Position> possibleMoves = figure.getPossibleMoves();
        for (Position move : possibleMoves) {
            System.out.println("Possible move: (" + move.getX() + ", " + move.getY() + ")");
        }
    }
}