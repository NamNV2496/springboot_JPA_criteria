package com.criteria.repository.custom;

import com.criteria.domain.oneToMany.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JoinQuery {

    //    public static <U> Specification<One> oneManySpecifications() {
//        log.info("Call join");
//        return (Root<One> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
//            ListJoin<One, Many> a1Join = root.joinList(One_.MANIES, JoinType.LEFT);
//            Predicate predicate = cb.and(cb.equal(a1Join.get(Many_.NAME),"nam"));
//            cq.where(predicate);
//            cq.select(a1Join.get(Many_.NAME));
////            cq.select(a1Join.get(One_.CITY));
//            return cb.and(predicate);
//        };
//    }
    public static <U> Specification<Dto> subQuerySpecifications() {
        log.info("Call SubQuery");
        return (Root<Dto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Subquery<Many> subquery = cq.subquery(Many.class);
            Root<Many> subqueryRoot = subquery.from(Many.class);
            subquery.select(subqueryRoot);
            Predicate predicate = cb.in(subqueryRoot.get(Many_.ONE_ID)).value(root.get(One_.ID));
            subquery.select(subqueryRoot).where(predicate);
            cq.multiselect(subqueryRoot.get(Many_.NAME), root.get(One_.CITY));
            return cb.exists(subquery);
        };
    }

    public static List<Tuple> test(EntityManager em) {
        log.info("test");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<One> personRoot = query.from(One.class);

        Subquery<Many> subquery = query.subquery(Many.class);
        Root<Many> subqueryRoot = subquery.from(Many.class);

        Predicate predicate = cb.equal(subqueryRoot.get(Many_.ONE_ID), personRoot.get(One_.ID));
        Join<One, Many> subJoin = subqueryRoot.join(Many_.ONE_ID, JoinType.INNER);
        subquery.select(subJoin.get(One_.ID)).where(predicate);

        query.multiselect(personRoot.get(One_.CITY), subquery.getSelection());
//        List<Tuple> results = em.createQuery(query).getResultList();

        return em.createQuery(query).getResultList();
//        return results;
    }


//    CriteriaBuilder builder = em.getCriteriaBuilder();
//    CriteriaQuery<NotificationInfo> cq = builder.createQuery(NotificationInfo.class);
//    Root<Notification> n = cq.from(Notification.class);
//
//    Subquery<Long> sqSent = cq.subquery(Long.class);
//    Root<NotificationUser> sqSentNU = sqSent.from(NotificationUser.class);
//sqSent.select(builder.count(sqSentNU));
//sqSent.where(
//        builder.equal(sqSentNU.get(NotificationUser_.notification), n),
//            builder.isNotNull(sqSentNU.get(NotificationUser_.sendDate))
//            );
//
//cq.select(
//        builder.construct(
//    NotificationInfo.class,
//            n.get(Notification_.idNotification),
//            n.get(Notification_.creationDate),
//            n.get(Notification_.suspendedDate),
//            n.get(Notification_.type),
//            n.get(Notification_.title),
//            n.get(Notification_.description),
//            sqSent.getSelection()
//            )
//            );
//
//em.createQuery(cq).getResultList();

}
