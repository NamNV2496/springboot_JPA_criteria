package com.criteria.domain.oneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class One {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String province;

    @OneToMany(mappedBy = "one1", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Many> manies;

}