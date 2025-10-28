package com.praktikum.whitebox.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk CourseFullException.
 * Tujuan: memastikan konstruktor dan pesan error bekerja benar.
 */
public class CourseFullExceptionTest {

    @Test
    void testMessageConstructor() {
        CourseFullException ex = new CourseFullException("Course is full");
        assertEquals("Course is full", ex.getMessage());
    }

    @Test
    void testNullMessageConstructor() {
        CourseFullException ex = new CourseFullException(null);
        assertNull(ex.getMessage());
    }
}
