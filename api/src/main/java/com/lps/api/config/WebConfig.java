package com.lps.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.api.models.Course;
import com.lps.api.models.Department;
import com.lps.api.models.Institution;
import com.lps.api.repositories.CourseRepository;
import com.lps.api.repositories.DepartmentRepository;
import com.lps.api.repositories.InstitutionRepository;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private InstitutionRepository institutionRepository;

        @Autowired
        private DepartmentRepository departmentRepository;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Value("${spring.profiles.active}")
        private String activeProfile;

        @Override
        public void run(String... args) throws Exception {
                if (activeProfile.equals("test")) {
                        Institution institution = new Institution(null, "Puc Minas", null);
                        institutionRepository.save(institution);
                        Department department = new Department(null, "Engenharia de Software", institution, null, null);
                        departmentRepository.save(department);
                        Course course = new Course(null, "Engenharia de Software", department, null);
                        courseRepository.save(course);
                }
        }
}