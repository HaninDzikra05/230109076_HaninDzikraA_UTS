package com.praktikum.whitebox.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk model Student.
 */
public class StudentTest {

    @Test
    void testConstructorAndGetters() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 4, 3.2, "ACTIVE");

        assertEquals("S01", s.getStudentId());
        assertEquals("Budi", s.getName());
        assertEquals("budi@mail.com", s.getEmail());
        assertEquals("TI", s.getMajor());
        assertEquals(4, s.getSemester());
        assertEquals(3.2, s.getGpa());
        assertEquals("ACTIVE", s.getAcademicStatus());
    }

    @Test
    void testSetters() {
        Student s = new Student("S02", "Andi", "andi@mail.com", "SI", 2, 3.0, "ACTIVE");

        s.setName("Andika");
        s.setEmail("andika@mail.com");
        s.setMajor("Informatika");
        s.setSemester(5);
        s.setGpa(3.5);
        s.setAcademicStatus("PROBATION");

        assertEquals("Andika", s.getName());
        assertEquals("andika@mail.com", s.getEmail());
        assertEquals("Informatika", s.getMajor());
        assertEquals(5, s.getSemester());
        assertEquals(3.5, s.getGpa());
        assertEquals("PROBATION", s.getAcademicStatus());
    }

    @Test
    void testToString() {
        Student s = new Student("S03", "Citra", "citra@mail.com", "MI", 3, 3.8, "ACTIVE");
        assertTrue(s.toString().contains("S03"));
        assertTrue(s.toString().contains("Citra"));
    }
}

