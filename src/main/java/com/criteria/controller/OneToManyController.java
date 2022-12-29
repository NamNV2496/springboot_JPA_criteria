package com.criteria.controller;

import com.criteria.domain.manyToMany.Many1;
import com.criteria.domain.manyToMany.Many2;
import com.criteria.domain.oneToMany.Many;
import com.criteria.domain.oneToMany.One;
import com.criteria.repository.Many1Repository;
import com.criteria.repository.Many2Repository;
import com.criteria.repository.ManyRepository;
import com.criteria.repository.OneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/oneToMany")
@RequiredArgsConstructor
public class OneToManyController {
    private final OneRepository oneRepository;
    private final ManyRepository manyRepository;
    private final Many2Repository many2Repository;
    private final Many1Repository many1Repository;

    @GetMapping("/get")
    public One get() {
        Optional<One> one = oneRepository.findById(1L);
        Set<Many> set= one.get().getManies();

        log.info("{}", set);
        return one.get();
    }
    @GetMapping("/get2")
    public Many get2() {
        return manyRepository.findById(1L).orElse(null);

    }
    @GetMapping("/get3")
    public Many2 get3() {
        return many2Repository.findById(1L).orElse(null);
    }
    @GetMapping("/get4")
    public Many1 get4() {
        return many1Repository.findById(1L).orElse(null);
    }

}
