package com.example.Praktika3_Ilin.repository;

import com.example.Praktika3_Ilin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>{
}
