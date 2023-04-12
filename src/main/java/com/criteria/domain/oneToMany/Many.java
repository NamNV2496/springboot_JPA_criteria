package com.criteria.domain.oneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "many")
public class Many {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "one_id")
    private Long oneId;
}