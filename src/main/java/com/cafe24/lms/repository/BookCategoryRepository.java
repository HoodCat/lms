package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long>{

}
