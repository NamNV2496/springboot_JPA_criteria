package com.criteria.controller;

import com.criteria.domain.oneToMany.Dto;
import com.criteria.domain.oneToMany.Many;
import com.criteria.domain.oneToMany.One;
import com.criteria.repository.OneRepository;
import com.criteria.repository.custom.JoinQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final OneRepository oneRepository;
    private final EntityManager em;

//    @GetMapping("/get")
//    public List<One> get() {
//        List<One> ones = oneRepository.findAll(JoinQuery.oneManySpecifications());
//
//        return ones;
//    }

    @GetMapping("/subquery")
    public List<Dto> subquery() {
        List<Dto> ones = oneRepository.findAll(JoinQuery.subQuerySpecifications());

        return ones;
    }

    @GetMapping("/test")
    public List<Tuple> test() {
        return JoinQuery.test(em);

    }
}
