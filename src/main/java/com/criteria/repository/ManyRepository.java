package com.criteria.repository;

import com.criteria.domain.oneToMany.Many;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManyRepository extends JpaRepository<Many, Long> {
}
