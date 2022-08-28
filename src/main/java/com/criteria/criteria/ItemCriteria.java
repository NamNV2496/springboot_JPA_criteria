package com.criteria.criteria;

import com.criteria.domain.Field;
import com.criteria.domain.Item;
import com.criteria.domain.Item_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemCriteria {

    public static Specification<Item> generateQuery() {
        return (Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> cb.and(
                cb.equal(root.get(Item_.ID), 1),
                cb.greaterThan(root.get(Item_.ITEM_PRICE), 100)
        );
    }

    public static <T extends Field> Specification<Item> generateQueryWithCustomize(List<T> data) {
        List<Predicate> list = new ArrayList<>();

        return (Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
            data.forEach(dt -> {
                if (!dt.getField().isEmpty()) {
                    switch (dt.getFieldType()) {
                        case GREATER_THAN:
                            switch (dt.getField()) {
                                case Item_.ID:
                                    list.add(cb.greaterThan(root.get(Item_.ID), dt.getValue()));
                                    break;
                                case Item_.ITEM_PRICE:
                                    list.add(cb.greaterThan(root.get(Item_.ITEM_PRICE), dt.getValue()));
                                    break;
                                case Item_.ITEM_NAME:
                                    list.add(cb.greaterThan(root.get(Item_.ITEM_NAME), dt.getValue()));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case LESS_THAN:
                            switch (dt.getField()) {
                                case Item_.ID:
                                    list.add(cb.lessThan(root.get(Item_.ID), dt.getValue()));
                                    break;
                                case Item_.ITEM_PRICE:
                                    list.add(cb.lessThan(root.get(Item_.ITEM_PRICE), dt.getValue()));
                                    break;
                                case Item_.ITEM_NAME:
                                    list.add(cb.lessThan(root.get(Item_.ITEM_NAME), dt.getValue()));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case LIKE:
                            switch (dt.getField()) {
                                case Item_.ID:
                                    list.add(cb.like(root.get(Item_.ID), "%" + dt.getValue() + "%"));
                                    break;
                                case Item_.ITEM_PRICE:
                                    list.add(cb.like(root.get(Item_.ITEM_PRICE), "%" + dt.getValue() + "%"));
                                    break;
                                case Item_.ITEM_NAME:
                                    list.add(cb.like(root.get(Item_.ITEM_NAME), "%" + dt.getValue() + "%"));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            switch (dt.getField()) {
                                case Item_.ID:
                                    list.add(cb.equal(root.get(Item_.ID), dt.getValue()));
                                    break;
                                case Item_.ITEM_PRICE:
                                    list.add(cb.equal(root.get(Item_.ITEM_PRICE), dt.getValue()));
                                    break;
                                case Item_.ITEM_NAME:
                                    list.add(cb.equal(root.get(Item_.ITEM_NAME), dt.getValue()));
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                }
            });
            return cb.and(list.toArray(new Predicate[]{}));
        };
    }
}
