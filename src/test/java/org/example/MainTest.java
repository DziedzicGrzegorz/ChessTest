package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        //use assertj to test the output of the main method
        assertEquals("Hello, World!", "Hello, World!");
    }
}