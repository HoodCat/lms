package com.cafe24.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.domain.enumeration.Role;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public boolean join(User user) {
        user.setRegDate(new Date());
        user.setRole(Role.USER);
        
        User savedUser = userRepository.save(user);
        if(savedUser == null) {
            return false;
        }
        return true;
    }
    
    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    
    public boolean isExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
