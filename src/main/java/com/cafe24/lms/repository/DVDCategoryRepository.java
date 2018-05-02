package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.DVDCategory;

public interface DVDCategoryRepository extends JpaRepository<DVDCategory, Long> {

}
