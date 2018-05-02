package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cd_category")
public class CDCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long no;
    @Column(name="name", nullable=false, length=50)
    private String name;
    
    public CDCategory() {}
    public CDCategory(Long no, String name) {
        this.no = no;
        this.name = name;
    }
    
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
    
    @Override
    public String toString() {
        return "CDCategory [no=" + no + ", name=" + name + "]";
    }
}
