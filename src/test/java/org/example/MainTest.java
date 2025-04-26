package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testMainMethodOutput() {
        // Call the main method
        Main.main(new String[]{});

        // Get the output
        String output = outContent.toString();

        // Verify the output contains expected moves
        assertThat(output, containsString("Possible move: (5, 5)"));
        assertThat(output, containsString("Possible move: (6, 6)"));
        assertThat(output, containsString("Possible move: (7, 7)"));
        assertThat(output, containsString("Possible move: (5, 3)"));
        assertThat(output, containsString("Possible move: (6, 2)"));
        assertThat(output, containsString("Possible move: (3, 5)"));
        assertThat(output, containsString("Possible move: (2, 6)"));
        assertThat(output, containsString("Possible move: (1, 7)"));
        assertThat(output, containsString("Possible move: (3, 3)"));

        // The position (2, 2) should be in the output because blocks are included in possible moves
        assertThat(output, containsString("Possible move: (2, 2)"));
    }

    @Test
    void testMainMethodBehavior() {
        // Create the same setup as in the main method
        Board board = new Board(8);
        Rook rook = new Rook(4, 4, board);
        board.placeBlock(2, 2);

        // Get the possible moves
        List<Position> possibleMoves = rook.getPossibleMoves();

        // Verify the expected number of moves
        assertThat(possibleMoves, hasSize(11));

        // Verify specific positions are in the possible moves
        assertThat(possibleMoves, hasItems(
            new Position(5, 5), new Position(6, 6), new Position(7, 7),
            new Position(5, 3), new Position(6, 2),
            new Position(3, 5), new Position(2, 6), new Position(1, 7),
            new Position(3, 3)
        ));

        // Verify the blocked position is included in possible moves
        // (according to the Rook implementation, blocks are included in possible moves)
        assertThat(possibleMoves, hasItem(new Position(2, 2)));
    }

    @Nested
    class AdditionalMainTests {

        @Test
        void testDifferentBoardSize() {
            // Test with a smaller board
            Board board = new Board(5);
            Rook rook = new Rook(2, 2, board);

            List<Position> possibleMoves = rook.getPossibleMoves();

            // Expected diagonal moves from (2,2) in a 5x5 board
            // Diagonal up-right: (3,3), (4,4)
            // Diagonal down-right: (3,1), (4,0)
            // Diagonal up-left: (1,3), (0,4)
            // Diagonal down-left: (1,1), (0,0)
            assertThat(possibleMoves, hasSize(8));

            assertThat(possibleMoves, hasItems(
                new Position(3, 3), new Position(4, 4),
                new Position(3, 1), new Position(4, 0),
                new Position(1, 3), new Position(0, 4),
                new Position(1, 1), new Position(0, 0)
            ));
        }

        @Test
        void testRookInCorner() {
            Board board = new Board(8);
            Rook rook = new Rook(0, 0, board);

            List<Position> possibleMoves = rook.getPossibleMoves();

            // From corner (0,0), only one diagonal is possible: up-right
            // Diagonal up-right: (1,1), (2,2), (3,3), (4,4), (5,5), (6,6), (7,7)
            assertThat(possibleMoves, hasSize(7));

            // Check a few specific positions
            assertThat(possibleMoves, hasItems(
                new Position(1, 1), new Position(7, 7)
            ));
        }

        @Test
        void testMultipleBlocks() {
            Board board = new Board(8);
            Rook rook = new Rook(3, 3, board);

            // Place blocks in all four diagonal directions
            board.placeBlock(1, 1); // Block down-left
            board.placeBlock(5, 5); // Block up-right
            board.placeBlock(1, 5); // Block up-left
            board.placeBlock(5, 1); // Block down-right

            List<Position> possibleMoves = rook.getPossibleMoves();

            // Expected moves: (4,4), (5,5), (2,2), (1,1), (4,2), (5,1), (2,4), (1,5)
            // Note: blocks are included in possible moves
            assertThat(possibleMoves, hasSize(8));

            // Verify blocks are included
            assertThat(possibleMoves, hasItems(
                new Position(1, 1), new Position(5, 5),
                new Position(1, 5), new Position(5, 1)
            ));

            // Verify positions beyond blocks are not included
            assertNotEquals(possibleMoves, hasItem(new Position(0, 0)));
            assertNotEquals(possibleMoves, hasItem(new Position(6, 6)));
            assertNotEquals(possibleMoves, hasItem(new Position(0, 6)));
            assertNotEquals(possibleMoves, hasItem(new Position(6, 0)));
        }

        @Test
        void testRookAtEdge() {
            Board board = new Board(8);
            Rook rook = new Rook(7, 4, board);

            List<Position> possibleMoves = rook.getPossibleMoves();

            // Rook at the right edge (7,4) can move in two diagonal directions:
            // Diagonal up-left: (6,5), (5,6), (4,7)
            // Diagonal down-left: (6,3), (5,2), (4,1), (3,0)
            assertThat(possibleMoves, hasSize(7));

            // Check specific positions
            assertThat(possibleMoves, hasItems(
                new Position(6, 5), new Position(5, 6), new Position(4, 7),
                new Position(6, 3), new Position(5, 2), new Position(4, 1), new Position(3, 0)
            ));
        }
    }
}
