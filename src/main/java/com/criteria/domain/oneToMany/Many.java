package com.criteria.domain.oneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Many {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "one_id", referencedColumnName = "id")
    private One one1;
}