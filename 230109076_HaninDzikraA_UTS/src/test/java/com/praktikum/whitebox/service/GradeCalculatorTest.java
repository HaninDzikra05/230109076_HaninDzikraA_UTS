package com.praktikum.whitebox.service;

import com.praktikum.whitebox.model.CourseGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk kelas GradeCalculator.
 * Tujuan:
 *  - Mencakup seluruh cabang logika (branch coverage)
 *  - Memastikan semua skenario validasi dan perhitungan berjalan benar
 *  - Menggunakan JUnit 5
 */
public class GradeCalculatorTest {

    private GradeCalculator calculator;

    @BeforeEach
    void init() {
        calculator = new GradeCalculator();
    }

    // ==============================================================
    //                     TEST METHOD: calculateGPA()
    // ==============================================================

    @Test
    void calculateGPA_ShouldReturnCorrectAverage() {
        List<CourseGrade> data = Arrays.asList(
                new CourseGrade("IF101", 3, 4.0),
                new CourseGrade("IF102", 2, 3.0),
                new CourseGrade("IF103", 1, 2.0)
        );

        double gpa = calculator.calculateGPA(data);
        assertEquals(3.33, gpa);  // (4*3 + 3*2 + 2*1) / 6 = 20/6 = 3.3333
    }

    @Test
    void calculateGPA_ShouldReturnZero_WhenListIsEmpty() {
        assertEquals(0.0, calculator.calculateGPA(Collections.emptyList()));
    }

    @Test
    void calculateGPA_ShouldReturnZero_WhenListIsNull() {
        assertEquals(0.0, calculator.calculateGPA(null));
    }

    @Test
    void calculateGPA_ShouldThrowException_WhenGradePointInvalid() {
        List<CourseGrade> invalid = List.of(new CourseGrade("IF201", 2, 4.6));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateGPA(invalid));

        assertTrue(ex.getMessage().contains("Invalid grade point"));
    }

    @Test
    void calculateGPA_ShouldReturnZero_WhenAllCreditsZero() {
        List<CourseGrade> data = List.of(new CourseGrade("IF301", 0, 3.0));
        assertEquals(0.0, calculator.calculateGPA(data));
    }

    // ==============================================================
    //               TEST METHOD: determineAcademicStatus()
    // ==============================================================

    @Test
    void determineStatus_Semester1_Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.3, 1));
    }

    @Test
    void determineStatus_Semester2_Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(1.9, 2));
    }

    @Test
    void determineStatus_Semester3_Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.3, 3));
    }

    @Test
    void determineStatus_Semester3_Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.1, 3));
    }

    @Test
    void determineStatus_Semester4_Suspended() {
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.9, 4));
    }

    @Test
    void determineStatus_Semester5_Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(3.0, 5));
    }

    @Test
    void determineStatus_Semester6_Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.3, 6));
    }

    @Test
    void determineStatus_Semester8_Suspended() {
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.4, 8));
    }

    @Test
    void determineStatus_ShouldThrowException_WhenGPAInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(4.8, 3));
    }

    @Test
    void determineStatus_ShouldThrowException_WhenSemesterInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(2.5, 0));
    }

    // ==============================================================
    //                  TEST METHOD: calculateMaxCredits()
    // ==============================================================

    @Test
    void calculateMaxCredits_ShouldReturn24_WhenGPAHigh() {
        assertEquals(24, calculator.calculateMaxCredits(3.5));
    }

    @Test
    void calculateMaxCredits_ShouldReturn21_WhenGPAIsBetween2_5And3() {
        assertEquals(21, calculator.calculateMaxCredits(2.7));
    }

    @Test
    void calculateMaxCredits_ShouldReturn18_WhenGPAIsBetween2And2_49() {
        assertEquals(18, calculator.calculateMaxCredits(2.2));
    }

    @Test
    void calculateMaxCredits_ShouldReturn15_WhenGPALow() {
        assertEquals(15, calculator.calculateMaxCredits(1.8));
    }

    @Test
    void calculateMaxCredits_ShouldThrowException_WhenGPAInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(4.6));
    }
}
