package com.criteria.domain;

import com.criteria.constant.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "field")
public class Folder {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    private String field;
    private String value;
    private FieldType fieldType;
}
