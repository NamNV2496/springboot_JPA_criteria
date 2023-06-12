package com.criteria.domain.oneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class Dto {
    private Long id;

    @Column(name = "city")
    private String city;
    private String name;
}
