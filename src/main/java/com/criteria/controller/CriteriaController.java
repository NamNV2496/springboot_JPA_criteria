package com.criteria.controller;

import com.criteria.constant.FieldType;
import com.criteria.criteria.ItemCriteria;
import com.criteria.domain.Field;
import com.criteria.domain.Item;
import com.criteria.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
