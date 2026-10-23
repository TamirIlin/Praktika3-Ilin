package com.example.Praktika3_Ilin.repository;

import com.example.Praktika3_Ilin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
