package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testConstructorAndGetters() {
        Position position = new Position(3, 5);
        assertEquals(3, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testSetters() {
        Position position = new Position(1, 1);
        position.setX(7);
        position.setY(8);
        assertEquals(7, position.getX());
        assertEquals(8, position.getY());
    }

    @Test
    void testEquals() {
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);
        Position position3 = new Position(3, 2);

        // Test reflexivity
        assertEquals(position1, position1);
        
        // Test symmetry
        assertEquals(position1, position2);
        assertEquals(position2, position1);
        
        // Test with different positions
        assertNotEquals(position1, position3);
        
        // Test with null
        assertNotEquals(position1, null);
        
        // Test with different type
        assertNotEquals(position1, "not a position");
    }

    @Test
    void testHashCode() {
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);
        Position position3 = new Position(3, 2);

        // Equal objects should have equal hash codes
        assertEquals(position1.hashCode(), position2.hashCode());
        
        // Different objects may have different hash codes
        // This is not guaranteed, but likely in this case
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    @Test
    void testToString() {
        Position position = new Position(4, 7);
        assertEquals("(4, 7)", position.toString());
    }

    @Test
    void testBoundaryValues() {
        // Test with minimum values
        Position minPosition = new Position(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, minPosition.getX());
        assertEquals(Integer.MIN_VALUE, minPosition.getY());
        
        // Test with maximum values
        Position maxPosition = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, maxPosition.getX());
        assertEquals(Integer.MAX_VALUE, maxPosition.getY());
    }

    @Test
    void testNegativeCoordinates() {
        Position position = new Position(-3, -5);
        assertEquals(-3, position.getX());
        assertEquals(-5, position.getY());
    }
}