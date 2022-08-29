package com.criteria.controller;

import com.criteria.constant.FieldType;
import com.criteria.criteria.ItemCriteria;
import com.criteria.domain.Field;
import com.criteria.domain.Item;
import com.criteria.repository.ExtendsPageableAndSortingRepository;
import com.criteria.repository.ItemRepository;
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
@RequiredArgsConstructor
public class CriteriaController {
    private final ItemRepository itemRepository;

    @GetMapping
    public List<Item> getItem() {
        Specification<Item> query = ItemCriteria.generateQuery();
        return itemRepository.findAll(query);
    }

    @GetMapping("/custom")
    public List<Item> getItemCustom(@RequestBody List<Field> listData) {
        if (listData.isEmpty()) {
            listData.add(new Field("id", "2", FieldType.EQUAL));
            listData.add(new Field("itemName", "item", FieldType.LIKE));
        }
        Specification<Item> query = ItemCriteria.generateQueryWithCustomize(listData);
        return itemRepository.findAll(query);
    }

    @GetMapping("/page")
    public Page<Item> getPage(Pageable pageable) {
        List<Field> list = new ArrayList<>();
        Specification<Item> spec = ItemCriteria.generateQueryWithCustomize(list);
        List<Sort.Order> orders = new ArrayList<>();

        Page<Item> ret = itemRepository.findAll(spec, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders)));
        System.out.println("TEST: List " + ret.getContent() + "PAGE NUMBER IS: " + ret.getNumber());
        return ret;
    }

    // other way
    private final ExtendsPageableAndSortingRepository exRepository;

    @PostMapping("/page")
    public Page<Item> findUsers(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
//        return exRepository.findAll(pageable); // it also OK
        return itemRepository.findAll(PageRequest.of(page, size));
    }
}
//    public Response<Page<PermissionResp>> page(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
//                                                       Pageable pageable, @RequestBody(required = false) PermissionFindReq req) {
