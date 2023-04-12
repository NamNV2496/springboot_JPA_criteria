package com.criteria.repository.custom;

import com.criteria.domain.oneToMany.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@Slf4j
@RequiredArgsConstructor
public class JoinQuery {
    public static <U> Specification<One> oneManySpecifications() {
        log.info("Call join");
        return (Root<One> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            ListJoin<One, Many> a1Join = root.joinList(One_.MANIES, JoinType.LEFT);
            Predicate predicate = cb.and(cb.equal(a1Join.get(Many_.NAME),"nam"));
            cq.where(predicate);
            cq.select(a1Join.get(Many_.NAME));
//            cq.select(a1Join.get(One_.CITY));
            return cb.and(predicate);
        };
    }
    public static <U> Specification<One> subQuerySpecifications() {
        log.info("Call SubQuery");
        return (Root<One> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Subquery<Many> subquery = cq.subquery(Many.class);
            Root<Many> subqueryRoot = subquery.from(Many.class);
            subquery.select(subqueryRoot);
            Predicate predicate = cb.in(subqueryRoot.get(Many_.ONE_ID)).value(root.get(One_.ID));
            subquery.select(subqueryRoot).where(predicate);
            return cb.exists(subquery);
        };
    }
}
