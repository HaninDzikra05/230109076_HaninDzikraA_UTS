package com.praktikum.whitebox.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk EnrollmentException.
 */
public class EnrollmentExceptionTest {

    @Test
    void testMessageConstructor() {
        EnrollmentException ex = new EnrollmentException("Enrollment failed");
        assertEquals("Enrollment failed", ex.getMessage());
    }

    @Test
    void testNullMessageConstructor() {
        EnrollmentException ex = new EnrollmentException(null);
        assertNull(ex.getMessage());
    }
}

