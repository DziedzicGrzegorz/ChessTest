package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void boardConstructorTest() {
        Board board = new Board(8);
        assertEquals(8, board.getSize());
    }

    @Test
    void boardConstructorWrongSizeTest() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0));
    }

    @BeforeAll
    static void setUp() {
        Board board = new Board(8);

    }



    @Test
    void checkPositionCollision() {
        List<Position> possibleMoves = new ArrayList<>();

        possibleMoves.add(new Position(5, 5));
        possibleMoves.add(new Position(6, 6));
        possibleMoves.add(new Position(7, 7));
        possibleMoves.add(new Position(5, 3));
        possibleMoves.add(new Position(6, 2));
        possibleMoves.add(new Position(7, 1));
        possibleMoves.add(new Position(3, 5));
        possibleMoves.add(new Position(2, 6));
        possibleMoves.add(new Position(1, 7));
        possibleMoves.add(new Position(3, 3));
        possibleMoves.add(new Position(2, 2));
        possibleMoves.add(new Position(1, 1));
        possibleMoves.add(new Position(0, 0));

        Figure figure = new Figure(4, 4, new Board(8));
        assertEquals(possibleMoves, figure.getPossibleMoves());
    }

    @Test
    void placeFigure() {
    }

    @Test
    void placeBlock() {
    }

    @Test
    void isOccupied() {
    }

    @Test
    void getSize() {
    }
}