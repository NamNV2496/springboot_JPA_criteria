package com.criteria.repository;

import com.criteria.domain.Field;
import com.criteria.domain.Folder;
import com.criteria.domain.Item;
import com.criteria.domain.query.FolderQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FolderRepository extends JpaRepository<FolderQuery, Long>, JpaSpecificationExecutor<FolderQuery> {
}
