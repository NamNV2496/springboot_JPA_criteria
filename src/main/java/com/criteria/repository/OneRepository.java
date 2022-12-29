package com.criteria.repository;

import com.criteria.domain.oneToMany.One;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneRepository extends JpaRepository<One, Long> {
}
