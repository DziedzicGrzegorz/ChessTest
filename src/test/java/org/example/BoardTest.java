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

    }


    @Test
    void checkPositionCollision() {
        List<Position> possibleMoves = new ArrayList<>();
        Figure figure = new Figure(4, 4, new Board(8));

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

        assertEquals(possibleMoves, figure.getPossibleMoves());
    }

    @Test
    void checkPositionCollisionWithBlockage() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);

        Figure figure = new Figure(4, 4, board);

        board.placeBlock(6, 6);
        board.placeBlock(6, 2);
        board.placeBlock(0, 0);

        possibleMoves.add(new Position(5, 5));
        possibleMoves.add(new Position(6, 6));
        possibleMoves.add(new Position(5, 3));
        possibleMoves.add(new Position(6, 2));
        possibleMoves.add(new Position(3, 5));
        possibleMoves.add(new Position(2, 6));
        possibleMoves.add(new Position(1, 7));
        possibleMoves.add(new Position(3, 3));
        possibleMoves.add(new Position(2, 2));
        possibleMoves.add(new Position(1, 1));
        possibleMoves.add(new Position(0, 0));

        assertEquals(possibleMoves, figure.getPossibleMoves());

    }

    @Test
    void checkPositionCollisionWithFigureAndBlockage() {
        List<Position> possibleMoves1 = new ArrayList<>();
        List<Position> possibleMoves2 = new ArrayList<>();

        Board board = new Board(8);

        Figure figure1 = new Figure(4, 4, board);
        Figure figure2 = new Figure(6, 6, board);

        board.placeBlock(6, 2);
        board.placeBlock(0, 0);

        possibleMoves1.add(new Position(5, 5));
        possibleMoves1.add(new Position(6, 6));
        possibleMoves1.add(new Position(5, 3));
        possibleMoves1.add(new Position(6, 2));
        possibleMoves1.add(new Position(3, 5));
        possibleMoves1.add(new Position(2, 6));
        possibleMoves1.add(new Position(1, 7));
        possibleMoves1.add(new Position(3, 3));
        possibleMoves1.add(new Position(2, 2));
        possibleMoves1.add(new Position(1, 1));
        possibleMoves1.add(new Position(0, 0));

        possibleMoves2.add(new Position(7, 7));
        possibleMoves2.add(new Position(7, 5));
        possibleMoves2.add(new Position(5, 7));
        possibleMoves2.add(new Position(5, 5));
        possibleMoves2.add(new Position(4, 4));

        assertEquals(possibleMoves1, figure1.getPossibleMoves());
        assertEquals(possibleMoves2, figure2.getPossibleMoves());

    }

    //one of four corners to check
    @Test
    void checkCollisionInLeftUpperCorner() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);

        Figure figure = new Figure(0, 0, board);

        possibleMoves.add(new Position(1, 1));
        possibleMoves.add(new Position(2, 2));
        possibleMoves.add(new Position(3, 3));
        possibleMoves.add(new Position(4, 4));
        possibleMoves.add(new Position(5, 5));
        possibleMoves.add(new Position(6, 6));
        possibleMoves.add(new Position(7, 7));

        assertEquals(possibleMoves, figure.getPossibleMoves());
    }

    @Test
    void placeFigure() {
        Board board = new Board(8);
        Figure figure = new Figure(4, 4, board);
        assertTrue(board.isFigure(4, 4));
    }

    @Test
    void placeFiureOutOfBounds() {
        Board board = new Board(8);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Figure(8, 8, board));
    }

    @Test
    void placeFigureOnBlock() {
        Board board = new Board(8);
        board.placeBlock(4, 4);
        assertThrows(IllegalArgumentException.class, () -> new Figure(4, 4, board));
    }

    @Test
    void placeFigureOnFigure() {
        Board board = new Board(8);
        Figure figure = new Figure(4, 4, board);
        assertThrows(IllegalArgumentException.class, () -> new Figure(4, 4, board));
    }

    @Test
    void placeBlock() {
        Board board = new Board(8);
        board.placeBlock(4, 4);
        assertTrue(board.isOccupied(4, 4));
    }

    @Test
    void placeBlockOutOfBounds() {
        Board board = new Board(8);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.placeBlock(8, 8));
    }

    @Test
    void isOccupied() {
    }

    @Test
    void getSize() {
        Board board = new Board(8);
        assertEquals(8, board.getSize());

    }

    @Test
    void getSizeWrongSize() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0));
    }
}