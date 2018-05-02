package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rent_item")
@IdClass(RentItemId.class)
public class RentItem {
    @Id
    @ManyToOne
    @JoinColumn(name="user_no", referencedColumnName="no")
    private User user;
    
    @Id
    @ManyToOne
    @JoinColumn(name="item_no", referencedColumnName="no")
    private Item item;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="rent_date", nullable=false)
    private Date rentDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="return_date", nullable=false)
    private Date returnDate;
    
    @Column(name="priority", nullable=false)
    private Long priority;
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Date getRentDate() {
        return rentDate;
    }
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public Long getPriority() {
        return priority;
    }
    public void setPriority(Long priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return "RentItem [rentDate=" + rentDate + ", returnDate=" + returnDate + ", priority=" + priority + "]";
    }
}
