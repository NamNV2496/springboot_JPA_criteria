package com.criteria.domain.manyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Many1 {

    @Id
    Long id;

    String teacherName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Many2_Many1",
            joinColumns = @JoinColumn(name = "many1_id"),
            inverseJoinColumns = @JoinColumn(name = "many2_id"))
    @JsonManagedReference
    List<Many2> students;
}
