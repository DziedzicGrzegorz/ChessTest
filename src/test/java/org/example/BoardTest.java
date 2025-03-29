package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.hamcrest.MatcherAssert;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    @Nested
    @DisplayName("Board Creation Tests")
    class BoardCreationTests {

        @ParameterizedTest
        @ValueSource(ints = {1, 3, 5, 8, 10, 100})
        @DisplayName("Should create board with valid sizes")
        void shouldCreateBoardWithValidSizes(int size) {
            Board board = new Board(size);

            MatcherAssert.assertThat(board.getSize(), is(size));
        }

        @Test
        void GetSize(){
            Board board = new Board(4);
            assertThat(board.getSize(), is(4));
        }

        @Test
        @DisplayName("Should throw exception when creating board with invalid size")
        void shouldThrowExceptionWhenCreatingBoardWithInvalidSize() {
            assertThrows(IllegalArgumentException.class, () -> new Board(0));
            assertThrows(IllegalArgumentException.class, () -> new Board(-1));
        }
    }

    @Nested
    @DisplayName("Figure Placement Tests")
    class RookPlacementTests {

        @ParameterizedTest
        @CsvSource({
                "3, 0, 0",
                "3, 1, 1",
                "5, 2, 3",
                "8, 7, 7"
        })
        @DisplayName("Should place figure at valid position")
        void shouldPlaceFigureAtValidPosition(int size, int x, int y) {
            Board board = new Board(size);

            board.placeFigure(x, y);

            MatcherAssert.assertThat(board.isFigure(x, y), is(true));
            MatcherAssert.assertThat(board.isBlock(x, y), is(false));
            MatcherAssert.assertThat(board.isOccupied(x, y), is(true));
        }

        @ParameterizedTest
        @CsvSource({
                "3, 3, 0",
                "3, 0, 3",
                "5, 5, 5",
                "8, -1, 0"
        })
        @DisplayName("Should handle out of bounds figures correctly")
        void shouldHandleOutOfBoundsFiguresCorrectly(int size, int x, int y) {
            Board board = new Board(size);

            assertThrows(IllegalArgumentException.class , () -> board.placeFigure(x, y));
        }
    }

    @Nested
    @DisplayName("Block Placement Tests")
    class BlockPlacementTests {

        @ParameterizedTest
        @CsvSource({
                "3, 0, 0",
                "3, 1, 1",
                "5, 2, 3",
                "8, 7, 7"
        })
        @DisplayName("Should place block at valid position")
        void shouldPlaceBlockAtValidPosition(int size, int x, int y) {
            Board board = new Board(size);

            board.placeBlock(x, y);

            MatcherAssert.assertThat(board.isBlock(x, y), is(true));
            MatcherAssert.assertThat(board.isFigure(x, y), is(false));
            MatcherAssert.assertThat(board.isOccupied(x, y), is(true));
        }

        @ParameterizedTest
        @CsvSource({
                "3, 3, 0",
                "3, 0, 3",
                "5, 5, 5",
                "8, -1, 0"
        })
        @DisplayName("Should throw exception when placing block out of bounds")
        void shouldThrowExceptionWhenPlacingBlockOutOfBounds(int size, int x, int y) {
            Board board = new Board(size);

            assertThrows(IllegalArgumentException.class, () -> board.placeBlock(x, y));
        }

        @Test
        @DisplayName("Should throw exception when placing block on occupied position")
        void shouldThrowExceptionWhenPlacingBlockOnOccupiedPosition() {
            Board board = new Board(5);
            int x = 2;
            int y = 3;

            board.placeBlock(x, y);

            assertThrows(IllegalArgumentException.class, () -> board.placeBlock(x, y));
        }

        @Test
        void checkPositionCollision() {
            List<Position> possibleMoves = new ArrayList<>();
            Rook rook = new Rook(4, 4, new Board(8));

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

            MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
        }
        @Test
        @DisplayName("Should throw exception when placing block on figure")
        void shouldThrowExceptionWhenPlacingBlockOnFigure() {
            // given
            Board board = new Board(5);
            int x = 2;
            int y = 3;

            // when
            board.placeFigure(x, y);

            // then
            assertThrows(IllegalArgumentException.class, () -> board.placeBlock(x, y));
        }
    }
    @Test
    void checkPositionCollisionWithBlockage() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);

        Rook rook = new Rook(4, 4, board);

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

        MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
    }
    @Test
    void checkPositionCollisionWithFigureAndBlockage() {
        List<Position> possibleMoves1 = new ArrayList<>();
        List<Position> possibleMoves2 = new ArrayList<>();

        Board board = new Board(8);

        Rook rook1 = new Rook(4, 4, board);
        Rook rook2 = new Rook(6, 6, board);

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

        MatcherAssert.assertThat(rook1.getPossibleMoves(), is(possibleMoves1));
        MatcherAssert.assertThat(rook2.getPossibleMoves(), is(possibleMoves2));
    }
    @Test
    void checkCollisionInLeftUpperCorner() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);

        Rook rook = new Rook(0, 0, board);

        possibleMoves.add(new Position(1, 1));
        possibleMoves.add(new Position(2, 2));
        possibleMoves.add(new Position(3, 3));
        possibleMoves.add(new Position(4, 4));
        possibleMoves.add(new Position(5, 5));
        possibleMoves.add(new Position(6, 6));
        possibleMoves.add(new Position(7, 7));

        MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
    }
    @Test
    void checkRightUpperCorner() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);
        Rook rook = new Rook(0, 7, board);

        possibleMoves.add(new Position(1, 6));
        possibleMoves.add(new Position(2, 5));
        possibleMoves.add(new Position(3, 4));
        possibleMoves.add(new Position(4, 3));
        possibleMoves.add(new Position(5, 2));
        possibleMoves.add(new Position(6, 1));
        possibleMoves.add(new Position(7, 0));

        MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
    }

    @Test
    void checkLeftLowerCorner() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);
        Rook rook = new Rook(7,0, board);

        possibleMoves.add(new Position(6,1));
        possibleMoves.add(new Position(5,2));
        possibleMoves.add(new Position(4,3));
        possibleMoves.add(new Position(3,4));
        possibleMoves.add(new Position(2,5));
        possibleMoves.add(new Position(1,6));
        possibleMoves.add(new Position(0,7));

        MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
    }

    @Test
    void checkRightLowerCorner() {
        List<Position> possibleMoves = new ArrayList<>();
        Board board = new Board(8);
        Rook rook = new Rook(7,7, board);

        possibleMoves.add(new Position(6,6));
        possibleMoves.add(new Position(5,5));
        possibleMoves.add(new Position(4,4));
        possibleMoves.add(new Position(3,3));
        possibleMoves.add(new Position(2,2));
        possibleMoves.add(new Position(1,1));
        possibleMoves.add(new Position(0,0));

        MatcherAssert.assertThat(rook.getPossibleMoves(), is(possibleMoves));
    }

    @Nested
    @DisplayName("Complex Board Scenarios")
    class ComplexBoardScenarios {

        @Test
        @DisplayName("Should handle multiple figures and blocks")
        void shouldHandleMultipleFiguresAndBlocks() {
            Board board = new Board(5);

            board.placeFigure(0, 0);
            board.placeFigure(4, 4);
            board.placeBlock(1, 1);
            board.placeBlock(3, 3);

            MatcherAssert.assertThat(board.isFigure(0, 0), is(true));
            MatcherAssert.assertThat(board.isFigure(4, 4), is(true));
            MatcherAssert.assertThat(board.isBlock(1, 1), is(true));
            MatcherAssert.assertThat(board.isBlock(3, 3), is(true));

            MatcherAssert.assertThat(board.isOccupied(2, 2), is(false));
        }

        @Test
        @DisplayName("Should correctly identify different positions")
        void shouldCorrectlyIdentifyDifferentPositions() {
            Board board = new Board(3);

            board.placeFigure(0, 0);
            board.placeBlock(1, 1);

            MatcherAssert.assertThat(board.isOccupied(2, 2), is(false));
            MatcherAssert.assertThat(board.isFigure(2, 2), is(false));

            MatcherAssert.assertThat(board.isBlock(2, 2), is(false));
            MatcherAssert.assertThat(board.isBlock(1, 1), is(true));

            MatcherAssert.assertThat(board.isFigure(1, 1), is(false));
            MatcherAssert.assertThat(board.isFigure(0, 0), is(true));
            MatcherAssert.assertThat(board.isBlock(0, 0), is(false));
        }
    }

    @Test
    @DisplayName("Should check position collision correctly")
    void shouldCheckPositionCollisionCorrectly() {
        Board board = new Board(3);

        board.placeFigure(0, 0);
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(0, 0));

        board.placeBlock(1, 1);
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(1, 1));

        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(3, 0));
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(0, 3));
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(-1, 0));
    }
}