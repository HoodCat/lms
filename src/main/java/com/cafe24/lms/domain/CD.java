package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cafe24.lms.domain.enumeration.Status;

@Entity
@DiscriminatorValue("C")
public class CD extends Item {
    @Column(name="artist", nullable=true, length=100)
    private String artist;
    
    @ManyToOne
    @JoinColumn(name="category_no", referencedColumnName="no")
    private CDCategory cdCategory;

    public CD() {super(null, null, null, null);}
    public CD(String artist, CDCategory cdCategory) {
        super(null, null, null, null);
        this.artist = artist;
        this.cdCategory = cdCategory;
    }
    public CD(Long no, String title, Status status, Date regDate, String artist, CDCategory cdCategory) {
        super(no, title, status, regDate);
        this.artist = artist;
        this.cdCategory = cdCategory;
    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public CDCategory getCdCategory() {
        return cdCategory;
    }

    public void setCdCategory(CDCategory cdCategory) {
        this.cdCategory = cdCategory;
    }

    @Override
    public String toString() {
        return "CD [artist=" + artist + ", cdCategory=" + cdCategory + ", getNo()=" + getNo() + ", getTitle()="
                + getTitle() + ", getStatus()=" + getStatus() + "]";
    }
    
}
