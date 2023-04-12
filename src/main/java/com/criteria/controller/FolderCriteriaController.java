package com.criteria.controller;

import com.criteria.constant.FieldType;
import com.criteria.criteria.FolderCriteria;
import com.criteria.criteria.ItemCriteria;
import com.criteria.domain.Field;
import com.criteria.domain.Folder;
import com.criteria.domain.Item;
import com.criteria.domain.query.FolderQuery;
import com.criteria.domain.query.ItemQuery;
import com.criteria.repository.ExtendsPageableAndSortingRepository;
import com.criteria.repository.FolderRepository;
import com.criteria.repository.ItemRepository;
import com.criteria.service.OneToOneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderCriteriaController {
    private final ItemRepository itemRepository;
    private final FolderRepository folderRepository;
    private final OneToOneService oneToOneService;

    @GetMapping("/test")
    public FolderQuery test() {
        return oneToOneService.getItem();
    }

    @GetMapping
    public List<FolderQuery> getItem() {
        Specification<FolderQuery> query = ItemCriteria.generateQuery();
        return folderRepository.findAll(query);
    }

    @GetMapping("/custom")
    public List<FolderQuery> getItemCustom(@RequestBody List<Field> listData) {
        if (listData.isEmpty()) {
            listData.add(new Field("id", "2", FieldType.EQUAL));
            listData.add(new Field("itemName", "item", FieldType.LIKE));
        }
        Specification<FolderQuery> query = FolderCriteria.generateQueryWithCustomize(listData);
        return folderRepository.findAll(query);
    }

    @GetMapping("/page")
    public Page<ItemQuery> getPage(@RequestParam Integer page, @RequestParam Integer size) {
        List<Field> list = new ArrayList<>();
        Specification<ItemQuery> spec = FolderCriteria.generateQueryWithCustomize(list);
        List<Sort.Order> orders = new ArrayList<>();

        Page<ItemQuery> ret = itemRepository.findAll(spec, PageRequest.of(page, size, Sort.by(orders)));
        System.out.println("TEST: List " + ret.getContent() + "PAGE NUMBER IS: " + ret.getNumber());
        return ret;
    }
//
//    // other way
//    private final ExtendsPageableAndSortingRepository exRepository;
//
//    @PostMapping("/page")
//    public Page<Item> findUsers(
//            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
//        Sort sortable = null;
//        if (sort.equals("ASC")) {
//            sortable = Sort.by("id").ascending();
//        }
//        if (sort.equals("DESC")) {
//            sortable = Sort.by("id").descending();
//        }
//        Pageable pageable = PageRequest.of(page, size, sortable);
////        return exRepository.findAll(pageable); // it also OK
//        return itemRepository.findAll(PageRequest.of(page, size));
//    }
}
