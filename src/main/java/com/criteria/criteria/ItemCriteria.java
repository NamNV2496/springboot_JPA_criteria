package com.criteria.criteria;

import com.criteria.domain.Field;
import com.criteria.domain.Item;
import com.criteria.domain.Item_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCriteria {

    public static Specification<Item> generateQuery() {
        return (Root<Item> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> cb.and(
                cb.equal(root.get(Item_.ID), 1),
                cb.greaterThan(root.get(Item_.ITEM_PRICE), 100)
        );
    }

    public static <T extends Field> Specification<Item> generateQueryWithCustomize(List<T> data) {
        List<Predicate> list = new ArrayList<>();
        if (data == null) {
            return null;
        }

        return (root, cq, cb) -> {
            data.forEach(dt -> {
                if (!dt.getField().isEmpty() && !dt.getValue().isEmpty()) {
                    switch (dt.getFieldType()) {
                        case EQUAL:
                            list.add(cb.equal(root.get(dt.getField()), dt.getValue()));
                            break;
                        case GREATER_THAN:
//                                cb.ge() >=
//                                cb.le <=
                            list.add(cb.greaterThan(root.get(dt.getField()), dt.getValue()));
                            break;
                        case LESS_THAN:
                            list.add(cb.lessThan(root.get(dt.getField()), dt.getValue()));
                            break;
                        case LIKE:
                            list.add(cb.like(root.get(dt.getField()), "%" + dt.getValue() + "%"));
                            break;
                        case ORDER_BY:
                            if (dt.getValue().equals("asc")) {
                                cq.orderBy(cb.asc(root.get(dt.getField())));
                            } else {
                                cq.orderBy(cb.desc(root.get(dt.getField())));
                            }
                            break;
                        case GROUP_BY:
                            switch (dt.getField()) {
                                case Item_.ID:
                                case Item_.ITEM_PRICE:
                                    Expression<Long> groupByExp = cb.function("Long", Long.class, root.get(dt.getField())).as(Long.class);
                                    cq.groupBy(groupByExp);
                                    break;
                                case Item_.ITEM_NAME:
                                case Item_.ITEM_DESCRIPTION:
                                    Expression<String> groupByExpString = cb.function("String", String.class, root.get(dt.getField())).as(String.class);
                                    cq.groupBy(groupByExpString);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
            return cb.and(list.toArray(new Predicate[]{}));
        };
    }
}
