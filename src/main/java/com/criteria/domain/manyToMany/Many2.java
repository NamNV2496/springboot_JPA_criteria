package com.criteria.domain.manyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
public class Many2 {
    @Id
    private Long id;

    private String studentName;

    @ManyToMany(mappedBy = "students")
    @JsonBackReference
    List<Many1> teachers;

}
