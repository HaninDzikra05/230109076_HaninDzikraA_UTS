package com.praktikum.whitebox.service;

import com.praktikum.whitebox.exception.*;
import com.praktikum.whitebox.model.Course;
import com.praktikum.whitebox.model.Enrollment;
import com.praktikum.whitebox.model.Student;
import com.praktikum.whitebox.repository.CourseRepository;
import com.praktikum.whitebox.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test untuk kelas EnrollmentService.
 * Tujuan:
 *  - Menguji seluruh jalur logika (branch coverage)
 *  - Menggunakan kombinasi MOCK dan STUB
 *  - Tidak mengubah fungsi dari versi asli
 */
public class EnrollmentServiceTest {

    private StudentRepository studentRepo;
    private CourseRepository courseRepo;
    private NotificationService notificationService;
    private GradeCalculator gradeCalculator;
    private EnrollmentService service;

    @BeforeEach
    void init() {
        studentRepo = mock(StudentRepository.class);
        courseRepo = mock(CourseRepository.class);
        notificationService = mock(NotificationService.class);
        gradeCalculator = mock(GradeCalculator.class);
        service = new EnrollmentService(studentRepo, courseRepo, notificationService, gradeCalculator);
    }

    // ===============================================================
    //                 TEST GROUP: enrollCourse()
    // ===============================================================

    @Test
    void enrollCourse_ShouldSucceed_WhenAllValid() {
        Student student = new Student("S01", "Budi", "budi@mail.com", "Informatika", 3, 3.2, "ACTIVE");
        Course course = new Course("CS101", "Pemrograman", 3, 30, 10, "Dosen A");

        when(studentRepo.findById("S01")).thenReturn(student);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(course);
        when(courseRepo.isPrerequisiteMet("S01", "CS101")).thenReturn(true);

        Enrollment result = service.enrollCourse("S01", "CS101");

        assertNotNull(result);
        assertEquals("APPROVED", result.getStatus());
        assertEquals("CS101", result.getCourseCode());
        verify(courseRepo).update(any(Course.class));
        verify(notificationService).sendEmail(eq("budi@mail.com"), anyString(), contains("Pemrograman"));
    }

    @Test
    void enrollCourse_ShouldThrow_WhenStudentNotFound() {
        when(studentRepo.findById("S99")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.enrollCourse("S99", "CS101"));
    }

    @Test
    void enrollCourse_ShouldThrow_WhenStudentSuspended() {
        Student s = new Student("S02", "Andi", "andi@mail.com", "TI", 5, 2.8, "SUSPENDED");
        when(studentRepo.findById("S02")).thenReturn(s);
        assertThrows(EnrollmentException.class, () -> service.enrollCourse("S02", "CS101"));
    }

    @Test
    void enrollCourse_ShouldThrow_WhenCourseNotFound() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 3, 3.2, "ACTIVE");
        when(studentRepo.findById("S01")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS404")).thenReturn(null);
        assertThrows(CourseNotFoundException.class, () -> service.enrollCourse("S01", "CS404"));
    }

    @Test
    void enrollCourse_ShouldThrow_WhenCourseFull() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 3, 3.2, "ACTIVE");
        Course c = new Course("CS101", "Pemrograman", 3, 30, 30, "Dosen A");

        when(studentRepo.findById("S01")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);

        assertThrows(CourseFullException.class, () -> service.enrollCourse("S01", "CS101"));
    }

    @Test
    void enrollCourse_ShouldThrow_WhenPrerequisiteNotMet() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 3, 3.2, "ACTIVE");
        Course c = new Course("CS101", "Pemrograman", 3, 30, 10, "Dosen A");

        when(studentRepo.findById("S01")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);
        when(courseRepo.isPrerequisiteMet("S01", "CS101")).thenReturn(false);

        assertThrows(PrerequisiteNotMetException.class, () -> service.enrollCourse("S01", "CS101"));
    }

    // ===============================================================
    //               TEST GROUP: validateCreditLimit()
    // ===============================================================

    @Test
    void validateCreditLimit_ShouldReturnTrue_WhenWithinLimit() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 4, 3.0, "ACTIVE");
        when(studentRepo.findById("S01")).thenReturn(s);
        when(gradeCalculator.calculateMaxCredits(3.0)).thenReturn(24);

        assertTrue(service.validateCreditLimit("S01", 20));
    }

    @Test
    void validateCreditLimit_ShouldReturnFalse_WhenExceedLimit() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 4, 2.3, "ACTIVE");
        when(studentRepo.findById("S01")).thenReturn(s);
        when(gradeCalculator.calculateMaxCredits(2.3)).thenReturn(18);

        assertFalse(service.validateCreditLimit("S01", 21));
    }

    @Test
    void validateCreditLimit_ShouldThrow_WhenStudentNotFound() {
        when(studentRepo.findById("S99")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.validateCreditLimit("S99", 20));
    }

    // ===============================================================
    //                    TEST GROUP: dropCourse()
    // ===============================================================

    @Test
    void dropCourse_ShouldSucceed_WhenStudentAndCourseExist() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 4, 3.1, "ACTIVE");
        Course c = new Course("CS101", "Pemrograman", 3, 30, 10, "Dosen A");

        when(studentRepo.findById("S01")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);

        service.dropCourse("S01", "CS101");

        verify(courseRepo).update(any(Course.class));
        verify(notificationService).sendEmail(eq("budi@mail.com"), anyString(), contains("Pemrograman"));
    }

    @Test
    void dropCourse_ShouldThrow_WhenStudentNotFound() {
        when(studentRepo.findById("S99")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.dropCourse("S99", "CS101"));
    }

    @Test
    void dropCourse_ShouldThrow_WhenCourseNotFound() {
        Student s = new Student("S01", "Budi", "budi@mail.com", "TI", 4, 3.1, "ACTIVE");
        when(studentRepo.findById("S01")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS404")).thenReturn(null);

        assertThrows(CourseNotFoundException.class, () -> service.dropCourse("S01", "CS404"));
    }
}
