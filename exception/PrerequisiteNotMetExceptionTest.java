package com.praktikum.whitebox.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk PrerequisiteNotMetException.
 */
public class PrerequisiteNotMetExceptionTest {

    @Test
    void testMessageConstructor() {
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Prerequisite not met");
        assertEquals("Prerequisite not met", ex.getMessage());
    }

    @Test
    void testNullMessageConstructor() {
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException(null);
        assertNull(ex.getMessage());
    }
}
