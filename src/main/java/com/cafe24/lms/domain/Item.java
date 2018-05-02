package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cafe24.lms.domain.enumeration.Status;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long no;
    
    @Column(name="title", nullable=false, length=50)
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false, columnDefinition="enum('LENDABLE', 'LENT', 'NOT_AVAILABLE')")
    private Status status;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_date", nullable=false)
    private Date regDate;
    
    public Item() {}
    public Item(Long no, String title, Status status, Date regDate) {
        super();
        this.no = no;
        this.title = title;
        this.status = status;
        this.regDate = regDate;
    }


    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    
}
