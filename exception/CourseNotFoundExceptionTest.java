package com.praktikum.whitebox.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk CourseNotFoundException.
 */
public class CourseNotFoundExceptionTest {

    @Test
    void testMessageConstructor() {
        CourseNotFoundException ex = new CourseNotFoundException("Course not found");
        assertEquals("Course not found", ex.getMessage());
    }

    @Test
    void testNullMessageConstructor() {
        CourseNotFoundException ex = new CourseNotFoundException(null);
        assertNull(ex.getMessage());
    }
}

