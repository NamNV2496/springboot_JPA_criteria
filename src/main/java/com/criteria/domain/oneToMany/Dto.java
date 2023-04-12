package com.criteria.domain.oneToMany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Dto {
    private Long id;

    @Column(name = "city")
    private String city;
}
