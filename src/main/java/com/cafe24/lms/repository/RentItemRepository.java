package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentItem;
import com.cafe24.lms.domain.RentItemId;

public interface RentItemRepository extends JpaRepository<RentItem, RentItemId> {
    @Query("SELECT MAX(r.priority) FROM RentItem AS r WHERE r.item=?1")
    public Long getMaxPriorityByItemNo(Item item);
    
    public List<RentItem> findByItem(Item item);
    public RentItem findByItemAndPriority(Item item, Long priority);
    
    @Query("SELECT r FROM RentItem AS r WHERE r.priority=?1 ORDER BY r.item.regDate DESC")
    public Page<RentItem> findByPriority(Long priority, Pageable pageable);
    
    @Query("SELECT r FROM RentItem AS r WHERE r.priority!=0 ORDER BY r.item.regDate DESC")
    public Page<RentItem> findReserveItem(Pageable pageable);
}
