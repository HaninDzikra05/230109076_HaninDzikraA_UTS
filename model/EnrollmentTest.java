package com.praktikum.whitebox.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk model Enrollment.
 */
public class EnrollmentTest {

    @Test
    void testConstructorAndGetterSetter() {
        Enrollment e = new Enrollment("S01", "CS101", "APPROVED");

        assertEquals("S01", e.getStudentId());
        assertEquals("CS101", e.getCourseCode());
        assertEquals("APPROVED", e.getStatus());

        e.setStatus("PENDING");
        assertEquals("PENDING", e.getStatus());
    }

    @Test
    void testToString() {
        Enrollment e = new Enrollment("S02", "CS102", "WAITLISTED");
        assertTrue(e.toString().contains("S02"));
        assertTrue(e.toString().contains("CS102"));
    }
}

