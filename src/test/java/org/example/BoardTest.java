package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Nested
    @DisplayName("Board Creation Tests")
    class BoardCreationTests {

        @ParameterizedTest
        @ValueSource(ints = {1, 3, 5, 8, 10, 100})
        @DisplayName("Should create board with valid sizes")
        void shouldCreateBoardWithValidSizes(int size) {
            // when
            Board board = new Board(size);

            // then
            assertEquals(size, board.getSize());
        }

        @Test
        @DisplayName("Should throw exception when creating board with invalid size")
        void shouldThrowExceptionWhenCreatingBoardWithInvalidSize() {
            // given
            int invalidSize = 0;

            // when & then
            assertThrows(IllegalArgumentException.class, () -> new Board(invalidSize));
            assertThrows(IllegalArgumentException.class, () -> new Board(-1));
        }
    }

    @Nested
    @DisplayName("Figure Placement Tests")
    class FigurePlacementTests {

        @ParameterizedTest
        @CsvSource({
                "3, 0, 0",
                "3, 1, 1",
                "5, 2, 3",
                "8, 7, 7"
        })
        @DisplayName("Should place figure at valid position")
        void shouldPlaceFigureAtValidPosition(int size, int x, int y) {
            // given
            Board board = new Board(size);

            // when
            board.placeFigure(x, y);

            // then
            assertTrue(board.isFigure(x, y));
            assertFalse(board.isBlock(x, y));
            assertTrue(board.isOccupied(x, y));
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
            // given
            Board board = new Board(size);

            // when & then
            // Out of bounds checking should be handled by your implementation
            // Here we're just testing the isOccupied and isFigure methods
            assertFalse(board.isOccupied(x, y));
            assertFalse(board.isFigure(x, y));
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
            // given
            Board board = new Board(size);

            // when
            board.placeBlock(x, y);

            // then
            assertTrue(board.isBlock(x, y));
            assertFalse(board.isFigure(x, y));
            assertTrue(board.isOccupied(x, y));
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
            // given
            Board board = new Board(size);

            // when & then
            assertThrows(IllegalArgumentException.class, () -> board.placeBlock(x, y));
        }

        @Test
        @DisplayName("Should throw exception when placing block on occupied position")
        void shouldThrowExceptionWhenPlacingBlockOnOccupiedPosition() {
            // given
            Board board = new Board(5);
            int x = 2;
            int y = 3;

            // when
            board.placeBlock(x, y);

            // then
            assertThrows(IllegalArgumentException.class, () -> board.placeBlock(x, y));
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

    @Nested
    @DisplayName("Complex Board Scenarios")
    class ComplexBoardScenarios {

        @Test
        @DisplayName("Should handle multiple figures and blocks")
        void shouldHandleMultipleFiguresAndBlocks() {
            // given
            Board board = new Board(5);

            // when
            board.placeFigure(0, 0);
            board.placeFigure(4, 4);
            board.placeBlock(1, 1);
            board.placeBlock(3, 3);

            // then
            assertTrue(board.isFigure(0, 0));
            assertTrue(board.isFigure(4, 4));
            assertTrue(board.isBlock(1, 1));
            assertTrue(board.isBlock(3, 3));

            assertFalse(board.isOccupied(2, 2));
        }

        @Test
        @DisplayName("Should correctly identify different positions")
        void shouldCorrectlyIdentifyDifferentPositions() {
            // given
            Board board = new Board(3);

            // when
            board.placeFigure(0, 0);
            board.placeBlock(1, 1);

            // then
            assertTrue(board.isFigure(0, 0));
            assertFalse(board.isBlock(0, 0));

            assertTrue(board.isBlock(1, 1));
            assertFalse(board.isFigure(1, 1));

            assertFalse(board.isOccupied(2, 2));
            assertFalse(board.isFigure(2, 2));
            assertFalse(board.isBlock(2, 2));
        }
    }

    @Test
    @DisplayName("Should check position collision correctly")
    void shouldCheckPositionCollisionCorrectly() {
        // given
        Board board = new Board(3);

        // 1. Test collision with figure
        board.placeFigure(0, 0);
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(0, 0));

        // 2. Test collision with block
        board.placeBlock(1, 1);
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(1, 1));

        // 3. Test out of bounds
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(3, 0));
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(0, 3));
        assertThrows(IllegalArgumentException.class, () -> board.checkPositionCollision(-1, 0));
    }
}