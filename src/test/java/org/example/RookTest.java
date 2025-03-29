package org.example;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RookTest {

    @Test
    void testGetPossibleMovesNoObstacles() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6), new Position(7, 7),
                new Position(5, 3), new Position(6, 2), new Position(7, 1),
                new Position(3, 5), new Position(2, 6), new Position(1, 7),
                new Position(3, 3), new Position(2, 2), new Position(1, 1), new Position(0, 0)
        );
        assertThat(rook.getPossibleMoves(), containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testGetPossibleMovesWithBlocks() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(6, 6);
        board.placeBlock(6, 2);
        board.placeBlock(0, 0);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6),
                new Position(5, 3), new Position(6, 2),
                new Position(3, 5), new Position(2, 6), new Position(1, 7),
                new Position(3, 3), new Position(2, 2), new Position(1, 1), new Position(0, 0)
        );
        assertThat(rook.getPossibleMoves(), containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testGetPossibleMovesAtEdge() {
        Board board = new Board(8);
        Rook rook = new Rook(0, 0, board);
        List<Position> expected = Arrays.asList(
                new Position(1, 1), new Position(2, 2), new Position(3, 3),
                new Position(4, 4), new Position(5, 5), new Position(6, 6), new Position(7, 7)
        );
        assertThat(rook.getPossibleMoves(), containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionNoObstacles() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6), new Position(7, 7)
        );
        assertThat(possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionWithOccupied() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(6, 6);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6)
        );
        assertThat(possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionImmediateInvalid() {
        Board board = new Board(8);
        Rook rook = new Rook(0, 0, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, -1, -1);
        MatcherAssert.assertThat(possibleMoves.isEmpty(),is(true));
    }

    @Test
    void testIsValidPosition() {
        Board board = new Board(8);
        Rook rook = new Rook(0, 0, board);
        MatcherAssert.assertThat(rook.isValidPosition(0, 0),is(true));
        MatcherAssert.assertThat(rook.isValidPosition(7, 7),is(true));
        MatcherAssert.assertThat(rook.isValidPosition(-1, 0),is(false));
        MatcherAssert.assertThat(rook.isValidPosition(0, -1),is(false));
        MatcherAssert.assertThat(rook.isValidPosition(8, 0),is(false));
        MatcherAssert.assertThat(rook.isValidPosition(0, 8),is(false));
    }

    @Test
    void testGetPossibleMovesSmallBoard() {
        Board board = new Board(1);
        Rook rook = new Rook(0, 0, board);
        List<Position> possibleMoves = rook.getPossibleMoves();
        MatcherAssert.assertThat(possibleMoves.isEmpty(),is(true));
    }

    @Test
    void testGetPossibleMovesBoardSizeTwo() {
        Board board = new Board(2);
        Rook rook = new Rook(0, 0, board);
        List<Position> expected = List.of(new Position(1, 1));
        assertThat(rook.getPossibleMoves(), containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionOppositeDiagonal() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(2, 2);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, -1, -1);
        List<Position> expected = Arrays.asList(
                new Position(3, 3), new Position(2, 2)
        );
        assertThat("Moves in direction (-1, -1) should include block at (2, 2) and stop",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionWithNoObstacles() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6), new Position(7, 7)
        );
        assertThat("Moves in direction (1, 1) should reach edge when no obstacles",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionFromEdge() {
        Board board = new Board(8);
        Rook rook = new Rook(0, 0, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = Arrays.asList(
                new Position(1, 1), new Position(2, 2), new Position(3, 3),
                new Position(4, 4), new Position(5, 5), new Position(6, 6), new Position(7, 7)
        );
        assertThat("Moves from (0, 0) in direction (1, 1) should reach opposite corner",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionImmediateBlock() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(5, 5);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = List.of(
                new Position(5, 5)
        );
        assertThat("Moves in direction (1, 1) should stop immediately at adjacent block",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionMultipleBlocks() {
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(5, 5);
        board.placeBlock(6, 6);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = List.of(
                new Position(5, 5)
        );
        assertThat("Moves in direction (1, 1) should stop at first block (5, 5)",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionOutOfBounds() {
        Board board = new Board(8);
        Rook rook = new Rook(0, 0, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, -1, -1);
        assertThat("Moves in direction (-1, -1) from (0, 0) should be empty due to out-of-bounds",
                possibleMoves, empty());
    }

    @Test
    void testCheckDirectionWithFigure() {
        Board board = new Board(8);
        Rook rook1 = new Rook(4, 4, board);
        new Rook(6, 6, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook1.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = Arrays.asList(
                new Position(5, 5), new Position(6, 6)
        );
        assertThat("Moves in direction (1, 1) should include figure at (6, 6) and stop",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }

    @Test
    void testCheckDirectionSmallBoard() {
        Board board = new Board(2);
        Rook rook = new Rook(0, 0, board);
        List<Position> possibleMoves = new ArrayList<>();
        rook.checkDirection(possibleMoves, 1, 1);
        List<Position> expected = List.of(
                new Position(1, 1)
        );
        assertThat("Moves in direction (1, 1) on 2x2 board should reach (1, 1)",
                possibleMoves, containsInAnyOrder(expected.toArray(new Position[0])));
    }
}