package com.praktikum.whitebox.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk model Course.
 * Tujuan: memastikan getter, setter, dan konstruktor bekerja dengan benar.
 */
public class CourseTest {

    @Test
    void testConstructorAndGetterSetter() {
        Course course = new Course("CS101", "Pemrograman", 3, 30, 10, "Dosen A");

        assertEquals("CS101", course.getCourseCode());
        assertEquals("Pemrograman", course.getCourseName());
        assertEquals(3, course.getCredits());
        assertEquals(30, course.getCapacity());
        assertEquals(10, course.getEnrolledCount());
        assertEquals("Dosen A", course.getLecturer());

        // Test setter
        course.setCourseName("Basis Data");
        assertEquals("Basis Data", course.getCourseName());
    }

    @Test
    void testToString() {
        Course course = new Course("CS102", "PBO", 3, 40, 20, "Dosen B");
        String str = course.toString();
        assertTrue(str.contains("CS102"));
        assertTrue(str.contains("PBO"));
    }
}
