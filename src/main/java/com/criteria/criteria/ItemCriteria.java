package com.criteria.criteria;

import com.criteria.domain.Field;
import com.criteria.domain.Folder_;
import com.criteria.domain.Item;
import com.criteria.domain.Item_;
import com.criteria.domain.query.FolderQuery;
import com.criteria.domain.query.FolderQuery_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCriteria {

    public static <T> Specification<T> generateQuery() {
        return (Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> cb.and(
                cb.equal(root.get(Item_.ID), 1),
                cb.greaterThan(root.get(Item_.ITEM_PRICE), 100)
        );
    }

    public static Specification<FolderQuery> generateQuery(String name) {
        return (Root<FolderQuery> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> cb.and(
                cb.like(root.get(FolderQuery_.NAME), '%' + name + '%')
        );
    }

    public static <T extends Field, U> Specification<U> generateQueryWithCustomize(List<T> data) {
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
                                    cq.multiselect(root.get(Item_.ID), cb.count(root)).groupBy(root.get(Item_.ITEM_PRICE));
//                                    Expression<Long> groupByExp = cb.function("Long", Long.class, root.get(dt.getField())).as(Long.class);
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
