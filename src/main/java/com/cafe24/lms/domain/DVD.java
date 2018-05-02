package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cafe24.lms.domain.enumeration.Status;

@Entity
@DiscriminatorValue("I")
public class DVD extends Item {
    @Column(name="distributor", nullable=true, length=100)
    private String distributor;
    
    @ManyToOne
    @JoinColumn(name="category_no", referencedColumnName="no")
    private DVDCategory dvdCategory;
    
    public DVD() {super(null, null, null, null);}
    public DVD(String distributor, DVDCategory dvdCategory) {
        super(null, null, null, null);
        this.distributor = distributor;
        this.dvdCategory = dvdCategory;
    }
    public DVD(Long no, String title, Status status, Date regDate, String distributor, DVDCategory dvdCategory) {
        super(no, title, status, regDate);
        this.distributor = distributor;
        this.dvdCategory = dvdCategory;
    }
    

    public String getDistributor() {
        return distributor;
    }
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
    public DVDCategory getDvdCategory() {
        return dvdCategory;
    }
    public void setDvdCategory(DVDCategory dvdCategory) {
        this.dvdCategory = dvdCategory;
    }
    
    @Override
    public String toString() {
        return "DVD [distributor=" + distributor + ", dvdCategory=" + dvdCategory + ", getNo()=" + getNo()
                + ", getTitle()=" + getTitle() + ", getStatus()=" + getStatus() + "]";
    }
    
}
