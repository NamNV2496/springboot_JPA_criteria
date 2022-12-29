package com.criteria.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class ItemQuery {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    @OneToOne(mappedBy = "itemQuery")
    private FolderQuery folderQuery;

}