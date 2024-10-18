package com.lps.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.models.Address;
import com.lps.api.models.Student;
import com.lps.api.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressService addressService;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student findByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }

    public Student save(Student student) {

        if (student.getName() == null && student.getName().isEmpty() && student.getCpf() == null
                && student.getCpf().isEmpty()) {
            throw new IllegalArgumentException("Name and CPF are required fields");
        }
        Student studentSaved = studentRepository.save(student);
        studentSaved.getAddress().setStudent(studentSaved);
        Address adr = addressService.save(studentSaved.getAddress());
        studentSaved.setAddress(adr);

        return studentRepository.save(studentSaved);
    }

    public void deleteById(Long id) {
        addressService.deleteByUserId(id);
        studentRepository.deleteById(id);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

}
