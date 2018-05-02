package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
    @Modifying // 벌크성 update, delete Query
    @Query(value="UPDATE Item AS i SET i.status=:#{#item.status} WHERE i.no=:#{#item.no}", nativeQuery=false)
    public int update(@Param("item")Item item);
}
