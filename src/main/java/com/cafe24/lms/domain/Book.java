package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cafe24.lms.domain.enumeration.Status;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    @Column(name="isbn", nullable=true, length=200)
    private String ISBN;
    
    @ManyToOne
    @JoinColumn(name="category_no", referencedColumnName="no")
    private BookCategory bookCategory;
    
    public Book() {
        super(null, null, null, null);
    }
    public Book(String ISBN, BookCategory bookCategory) {
        super(null, null, null, null);
        this.ISBN = ISBN;
        this.bookCategory = bookCategory;
    }
    public Book(Long no, String title, Status status, Date regDate, String ISBN, BookCategory bookCategory) {
        super(no, title, status, regDate);
        this.ISBN = ISBN;
        this.bookCategory = bookCategory;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public BookCategory getBookCategory() {
        return bookCategory;
    }
    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
    
    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", bookCategory=" + bookCategory + ", getNo()=" + getNo() + ", getTitle()="
                + getTitle() + ", getStatus()=" + getStatus() + "]";
    }
    
}
