package com.praktikum.whitebox.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk StudentNotFoundException.
 */
public class StudentNotFoundExceptionTest {

    @Test
    void testMessageConstructor() {
        StudentNotFoundException ex = new StudentNotFoundException("Student not found");
        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    void testNullMessageConstructor() {
        StudentNotFoundException ex = new StudentNotFoundException(null);
        assertNull(ex.getMessage());
    }
}
