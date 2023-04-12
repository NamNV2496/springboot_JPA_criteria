package com.criteria.domain.oneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "one")
public class One {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "city")
    private String city;
    private String province;

    @OneToMany(mappedBy = "oneId")
    private List<Many> manies;

}