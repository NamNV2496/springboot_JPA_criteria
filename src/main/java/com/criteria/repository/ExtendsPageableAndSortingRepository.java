package com.criteria.repository;

import com.criteria.domain.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExtendsPageableAndSortingRepository extends PagingAndSortingRepository<Item, Long> {
}
