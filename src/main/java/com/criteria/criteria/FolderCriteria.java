package com.criteria.criteria;

import com.criteria.domain.Field;
import com.criteria.domain.Folder;
import com.criteria.domain.Item;
import com.criteria.domain.query.FolderQuery;
import com.criteria.domain.query.FolderQuery_;
import com.criteria.domain.query.ItemQuery;
import com.criteria.domain.query.ItemQuery_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class FolderCriteria {

    public static <T extends Field, U> Specification<FolderQuery> generateQueryWithCustomize(List<T> data) {
        List<Predicate> list = new ArrayList<>();
        if (data == null) {
            return null;
        }

        return (Root<FolderQuery> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            data.forEach(dt -> {
                if (!dt.getField().isEmpty() && !dt.getValue().isEmpty()) {
                    switch (dt.getFieldType()) {
                        case EQUAL:
//                            list.add(cb.equal(root.get(dt.getField()), dt.getValue()));
                            Join<Folder, Item> fieldJoin = root.join(FolderQuery_.ITEM_QUERY);
                            list.add(cb.equal(fieldJoin.get(ItemQuery_.ITEM_NAME), dt.getValue()));
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
                        default:
                            break;
                    }
                }
            });
            return cb.and(list.toArray(new Predicate[]{}));
        };
    }
}
