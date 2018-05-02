package com.cafe24.lms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.cafe24.lms.domain.BookCategory;
import com.cafe24.lms.domain.CD;
import com.cafe24.lms.domain.CDCategory;
import com.cafe24.lms.domain.DVD;
import com.cafe24.lms.domain.DVDCategory;
import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentItem;
import com.cafe24.lms.domain.RentItemId;
import com.cafe24.lms.domain.enumeration.Status;
import com.cafe24.lms.repository.BookCategoryRepository;
import com.cafe24.lms.repository.BookRepository;
import com.cafe24.lms.repository.CDCategoryRepository;
import com.cafe24.lms.repository.CDRepository;
import com.cafe24.lms.repository.DVDCategoryRepository;
import com.cafe24.lms.repository.DVDRepository;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentItemRepository;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class ItemService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CDRepository cdRepository;
    @Autowired
    private DVDRepository dvdRepository;
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Autowired
    private CDCategoryRepository cdCategoryRepository;
    @Autowired
    private DVDCategoryRepository dvdCategoryRepository;
    @Autowired
    private RentItemRepository rentItemRepository;
    
    private Long totalCount;
    private Integer totalPage;
    
    public Long getTotalCount() {
        return totalCount;
    }
    public Integer getTotalPage() {
        return totalPage;
    }


    public void initData() {
        List<BookCategory> bookCategories = new ArrayList<BookCategory>();
        bookCategories.add(new BookCategory(null, "소설"));
        bookCategories.add(new BookCategory(null, "IT"));
        bookCategories.add(new BookCategory(null, "에세이"));
        bookCategories.add(new BookCategory(null, "사회"));
        bookCategories.add(new BookCategory(null, "역사"));
        bookCategories.add(new BookCategory(null, "과학"));
        bookCategoryRepository.save(bookCategories);
        
        List<CDCategory> cdCategories = new ArrayList<CDCategory>();
        cdCategories.add(new CDCategory(null, "발라드"));
        cdCategories.add(new CDCategory(null, "댄스"));
        cdCategories.add(new CDCategory(null, "랩/힙합"));
        cdCategories.add(new CDCategory(null, "R&B"));
        cdCategories.add(new CDCategory(null, "락/메탈"));
        cdCategories.add(new CDCategory(null, "트로트"));
        cdCategoryRepository.save(cdCategories);
        
        List<DVDCategory> dvdCategories = new ArrayList<DVDCategory>();
        dvdCategories.add(new DVDCategory(null, "액션"));
        dvdCategories.add(new DVDCategory(null, "SF"));
        dvdCategories.add(new DVDCategory(null, "코미디"));
        dvdCategories.add(new DVDCategory(null, "스릴러"));
        dvdCategories.add(new DVDCategory(null, "전쟁"));
        dvdCategories.add(new DVDCategory(null, "판타지"));
        dvdCategories.add(new DVDCategory(null, "드라마"));
        dvdCategoryRepository.save(dvdCategories);
        
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(null, "세상의 중심에서 사랑을 외친 짐승", Status.LENDABLE, new Date(), "", bookCategories.get(0)));
        books.add(new Book(null, "Java의 정석 [3판]", Status.LENDABLE, new Date(), "", bookCategories.get(1)));
        books.add(new Book(null, "죽은 자로 하여금", Status.LENDABLE, new Date(), "", bookCategories.get(0)));
        books.add(new Book(null, "나는 미생물과 산다", Status.LENDABLE, new Date(), "", bookCategories.get(5)));
        bookRepository.save(books);
        
        List<DVD> dvds = new ArrayList<DVD>();
        dvds.add(new DVD(null, "파리로 가는 길", Status.LENDABLE, new Date(), "", dvdCategories.get(6)));
        dvds.add(new DVD(null, "어벤져스 3", Status.LENDABLE, new Date(), "", dvdCategories.get(1)));
        dvds.add(new DVD(null, "마션", Status.LENDABLE, new Date(), "", dvdCategories.get(1)));
        dvds.add(new DVD(null, "곤지암", Status.LENDABLE, new Date(), "", dvdCategories.get(3)));
        dvdRepository.save(dvds);
        
        List<CD> cds = new ArrayList<CD>();
        cds.add(new CD(null, "Balloon", Status.LENDABLE, new Date(), "메이다니 (MAYDONI)", cdCategories.get(0)));
        cds.add(new CD(null, "중2병 (8th Grade Syndrome)", Status.LENDABLE, new Date(), "하이큐티 (HI CUTIE)", cdCategories.get(1)));
        cds.add(new CD(null, "Hands In The Air (Feat. Jay Park)", Status.LENDABLE, new Date(), "TED PARK", cdCategories.get(2)));
        cds.add(new CD(null, "니가 싫지 않아 (Don`t leave me)", Status.LENDABLE, new Date(), "Racy", cdCategories.get(3)));
        cdRepository.save(cds);
    }
    
    public List<Map<String, Object>> getItemList() { 
        return getItemList(0);
    }
    
    public List<Map<String, Object>> getItemList(int page) { 
        List<Map<String, Object>> resultList = new ArrayList<>();
        PageRequest pageRequest = new PageRequest(page, 5, Direction.DESC, "regDate");
        Page<Item> content = itemRepository.findAll(pageRequest);

        totalCount = content.getTotalElements();
        totalPage = content.getTotalPages();
        
        for(Item item: content.getContent()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("no", item.getNo());
            entry.put("title", item.getTitle());
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

    public boolean rent(RentItemId rentItemId) {
        Item item = itemRepository.findOne(rentItemId.getItem());
        if(Status.LENDABLE.equals(item.getStatus()) == false) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date rentDate = calendar.getTime();
        calendar.add(Calendar.DATE, 7);
        Date returnDate = calendar.getTime();
        
        RentItem rentItem = new RentItem();
        rentItem.setUser(userRepository.findOne(rentItemId.getUser()));
        rentItem.setItem(item);
        rentItem.setRentDate(rentDate);
        rentItem.setReturnDate(returnDate);
        rentItem.setPriority(0L);
        
        rentItemRepository.save(rentItem);
        item.setStatus(Status.LENT);
        itemRepository.update(item);
        return true;
    }
    
    public boolean reserve(RentItemId rentItemId) {
        // 해당아이템을 예약했는지 확인
        if(rentItemRepository.exists(rentItemId)) {
            return false;
        };
        
        // 현재 상품이 예약가능한지 확인
        Item item = itemRepository.findOne(rentItemId.getItem());
        if(Status.LENDABLE.equals(item.getStatus())) {
            return false;
        }
        
        Long priority = rentItemRepository.getMaxPriorityByItemNo(item);
        RentItem prevItem = rentItemRepository.findByItemAndPriority(item, priority);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prevItem.getReturnDate());
        Date rentDate = calendar.getTime();
        calendar.add(Calendar.DATE, 7);
        Date returnDate = calendar.getTime();
        
        RentItem rentItem = new RentItem();
        rentItem.setUser(userRepository.findOne(rentItemId.getUser()));
        rentItem.setItem(item);
        rentItem.setRentDate(rentDate);
        rentItem.setReturnDate(returnDate);
        rentItem.setPriority(priority + 1);
        
        rentItemRepository.save(rentItem);
        return true;
    }
}
