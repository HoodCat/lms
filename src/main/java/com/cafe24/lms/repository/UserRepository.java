package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);
}
