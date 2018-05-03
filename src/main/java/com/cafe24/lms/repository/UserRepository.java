package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cafe24.lms.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);
    @Query("SELECT COUNT(u)>0 FROM User AS u WHERE u.email=?1")
    public boolean existsByEmail(String email);
}
