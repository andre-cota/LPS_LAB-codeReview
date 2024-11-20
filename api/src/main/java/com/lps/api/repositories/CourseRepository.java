package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);

}
