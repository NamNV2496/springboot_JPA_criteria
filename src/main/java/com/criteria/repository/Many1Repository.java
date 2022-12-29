package com.criteria.repository;

import com.criteria.domain.manyToMany.Many1;
import com.criteria.domain.manyToMany.Many2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Many1Repository extends JpaRepository<Many1, Long> {
}
