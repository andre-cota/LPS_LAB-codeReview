package com.lps.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lps.api.models.Company;
import com.lps.api.models.Course;
import com.lps.api.models.Department;
import com.lps.api.models.Institution;
import com.lps.api.repositories.CompanyRepository;
import com.lps.api.repositories.CourseRepository;
import com.lps.api.repositories.DepartmentRepository;
import com.lps.api.repositories.InstitutionRepository;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {
  
        @Autowired
        private InstitutionRepository institutionRepository;
        
        @Autowired
        private DepartmentRepository departmentRepository;
        
        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private CompanyRepository companyRepository;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }

        @Value("${spring.profiles.active}")
        private String activeProfile;

        @Override
        public void run(String... args) throws Exception {
                if(!activeProfile.equals("test")) {
                        return;
                }
                
                Institution institution1 = new Institution(null, "Puc Minas", null);
                Department department1 = new Department(null, "Engenharia de Software", institution1, null, null);
                Course course1 = new Course(null, "Engenharia de Software", department1, null);
                Company company1 = new Company(null, "Empresa1", "empresa1@gmail.com", "123");

                institutionRepository.saveAll(Arrays.asList(institution1));
                departmentRepository.saveAll(Arrays.asList(department1));
                courseRepository.saveAll(Arrays.asList(course1));
                companyRepository.saveAll(Arrays.asList(company1));
        }
}