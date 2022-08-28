package com.criteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    private Long id;
    private String itemName;
    private String itemDescription;
    private Integer itemPrice;

}