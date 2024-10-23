package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);

}
