package com.criteria.repository;

import com.criteria.domain.Item;
import com.criteria.domain.query.FolderQuery;
import com.criteria.domain.query.ItemQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<FolderQuery> {
    List<FolderQuery> findAll(Specification<FolderQuery> query);
}
