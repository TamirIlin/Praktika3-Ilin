package com.example.Praktika3_Ilin.service;

import com.example.Praktika3_Ilin.repository.StudentRepository;
import com.example.Praktika3_Ilin.model.Student;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
