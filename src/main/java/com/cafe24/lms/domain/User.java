package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cafe24.lms.domain.enumeration.Gender;
import com.cafe24.lms.domain.enumeration.Role;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
    @Column(name="name", nullable=false, length=50)
	private String name;
    
    @Column(name="email", nullable=false)
	private String email;
    
    @Column(name="password", nullable=false, length=64)
	private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name="gender", nullable=false, columnDefinition="enum('MALE', 'FEMALE')")
	private Gender gender;
    
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable=false, columnDefinition="enum('ADMIN', 'USER')")
	private Role role;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date", nullable=false)
	private Date regDate;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
                + gender + ", role=" + role + ", regDate=" + regDate + "]";
    }
	
}
