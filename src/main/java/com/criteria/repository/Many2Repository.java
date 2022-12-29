package com.criteria.repository;

import com.criteria.domain.manyToMany.Many2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Many2Repository extends JpaRepository<Many2, Long> {
}
