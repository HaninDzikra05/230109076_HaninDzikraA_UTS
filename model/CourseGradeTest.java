package com.praktikum.whitebox.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk model CourseGrade.
 */
public class CourseGradeTest {

    @Test
    void testConstructorAndGetters() {
        CourseGrade grade = new CourseGrade("IF101", 3, 4.0);

        assertEquals("IF101", grade.getCourseCode());
        assertEquals(3, grade.getCredits());
        assertEquals(4.0, grade.getGradePoint());
    }

    @Test
    void testSetterMethods() {
        CourseGrade grade = new CourseGrade("IF202", 2, 3.0);

        grade.setCourseCode("IF203");
        grade.setCredits(4);
        grade.setGradePoint(3.5);

        assertEquals("IF203", grade.getCourseCode());
        assertEquals(4, grade.getCredits());
        assertEquals(3.5, grade.getGradePoint());
    }

    @Test
    void testToString() {
        CourseGrade grade = new CourseGrade("IF204", 3, 3.7);
        assertTrue(grade.toString().contains("IF204"));
    }
}

