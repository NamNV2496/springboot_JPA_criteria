//package com.criteria.criteria;
//
//import com.criteria.domain.Item;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//public class CriteriaUpdate {
//    public List<Item> getAllitems() {
//        EntityManager em = null;
//        CriteriaBuilder cb = null;
//        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
//        Root<Item> from = cq.from(Item.class);
////    cq.select(Item);
//        TypedQuery<Item> q = em.createQuery(cq);
//        List<Item> allitems = q.getResultList();
//        return allitems;
//    }
//}
