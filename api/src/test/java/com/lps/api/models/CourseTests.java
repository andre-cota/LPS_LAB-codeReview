package com.lps.api.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CourseTest {

    private Department department;
    private Course course;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Computer Science");

        student1 = new Student();
        student1.setId(1L);
        student1.setName("Alice");

        student2 = new Student();
        student2.setId(2L);
        student2.setName("Bob");

        course = new Course();
        course.setId(1L);
        course.setName("Software Engineering");
        course.setDepartment(department);
    }

    @Test
    void testCourseCreation() {
        assertEquals(1L, course.getId());
        assertEquals("Software Engineering", course.getName());
        assertEquals(department, course.getDepartment());
    }

}
