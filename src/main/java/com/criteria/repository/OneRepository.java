package com.criteria.repository;

import com.criteria.domain.oneToMany.Dto;
import com.criteria.domain.oneToMany.One;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OneRepository extends JpaRepository<One, Long>, JpaSpecificationExecutor<Dto> {
}
