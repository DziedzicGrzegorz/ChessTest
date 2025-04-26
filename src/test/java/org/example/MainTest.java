package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
