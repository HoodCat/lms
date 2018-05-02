package com.cafe24.lms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Book;
import com.cafe24.lms.domain.CD;
import com.cafe24.lms.domain.DVD;
import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentItem;
import com.cafe24.lms.repository.RentItemRepository;

@Service
@Transactional
public class AdminService {
    private Long totalCount;
    private Integer totalPage;
    
    @Autowired
    private RentItemRepository rentItemRepository;
    
    public Long getTotalCount() {
        return totalCount;
    }
    public Integer getTotalPage() {
        return totalPage;
    }

    public List<Map<String, Object>> getRentItemList(int page) { 
        List<Map<String, Object>> resultList = new ArrayList<>();
        PageRequest pageRequest = new PageRequest(page, 5);
        Page<RentItem> content = rentItemRepository.findByPriority(0L, pageRequest);

        totalCount = content.getTotalElements();
        totalPage = content.getTotalPages();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        
        for(RentItem rentItem: content.getContent()) {
            Map<String, Object> entry = new HashMap<>();
            Item item = rentItem.getItem();
            entry.put("title", item.getTitle());
            entry.put("rentDate", dataFormat.format(rentItem.getRentDate()));
            entry.put("returnDate", dataFormat.format(rentItem.getReturnDate()));
            if(item instanceof Book) {
                Book bookItem = (Book)item;
                entry.put("category", "도서(" + bookItem.getBookCategory().getName() + ")");
            } else if(item instanceof CD) {
                CD cdItem = (CD)item;
                entry.put("category", "음반(" + cdItem.getCdCategory().getName() + ")");
            } else if(item instanceof DVD) {
                DVD dvdItem = (DVD)item;
                entry.put("category", "DVD(" + dvdItem.getDvdCategory().getName() + ")");
            }
            
            resultList.add(entry);
        }
        return resultList;
    }
    
    public List<Map<String, Object>> getReserveItemList(int page) { 
        List<Map<String, Object>> resultList = new ArrayList<>();
        PageRequest pageRequest = new PageRequest(page, 5);
        Page<RentItem> content = rentItemRepository.findReserveItem(pageRequest);

        totalCount = content.getTotalElements();
        totalPage = content.getTotalPages();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        
        for(RentItem rentItem: content.getContent()) {
            Map<String, Object> entry = new HashMap<>();
            Item item = rentItem.getItem();
            entry.put("title", item.getTitle());
            entry.put("rentDate", dataFormat.format(rentItem.getRentDate()));
            entry.put("returnDate", dataFormat.format(rentItem.getReturnDate()));
            if(item instanceof Book) {
                Book bookItem = (Book)item;
                entry.put("category", "도서(" + bookItem.getBookCategory().getName() + ")");
            } else if(item instanceof CD) {
                CD cdItem = (CD)item;
                entry.put("category", "음반(" + cdItem.getCdCategory().getName() + ")");
            } else if(item instanceof DVD) {
                DVD dvdItem = (DVD)item;
                entry.put("category", "DVD(" + dvdItem.getDvdCategory().getName() + ")");
            }
            
            resultList.add(entry);
        }
        return resultList;
    }
}
