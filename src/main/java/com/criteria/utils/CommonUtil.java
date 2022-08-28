//package com.criteria.utils;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.Predicate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class CommonUtil {
//
//    public static Predicate and(CriteriaBuilder cb, Predicate... var1) throws Exception {
//        List<Predicate> all = findAll(Arrays.asList(var1), (index, item) -> item != null);
//        Predicate predicate = null;
//        if(CommonUtil.isTrue(all)){
//            predicate = cb.and(all.toArray(new Predicate[]{}));
//        }
//        return predicate;
//    }
//
//    public static <T> List<T> findAll(List<T> list, final Find<T> findAll_) throws Exception {
//
//        final List<T> newList = new ArrayList();
//
//        if (!CommonUtil.isTrue(list)) {
//            return newList;
//        }
//
//        try {
//            each(list, (int index, T item) -> {
//                if (findAll_.do_(index, item)) {
//                    newList.add(item);
//                }
//            });
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return newList;
//
//    }
//    public static <T> void each(List<T> list, Each<T> each) throws Exception {
//
//        if (!CommonUtil.isTrue(list)) {
//            return;
//        }
//
//        for (int index = 0; index < list.size(); index++) {
//            each.do_(index, list.get(index));
//        }
//    }
//
//    public static Boolean isTrue(Object value) {
//        boolean result = true;
//        if (value == null) {
//            result = false;
//        }
//
//        if (value instanceof String) {
//            result = !((String) value).trim().isEmpty();
//        }
//
//        if (value instanceof Number) {
//            result = !((Number) value).equals(Long.valueOf(0));
//        }
//
//        if (value instanceof Boolean) {
//            result = (Boolean) value;
//        }
//
//
//        if (value instanceof Object[]) {
//            result = ((Object[]) value).length > 0;
//        }
//
//        return result;
//    }
//
//    public interface Each<T> {
//
//        void do_(int index, T item) throws Exception;
//    }
//
//    public interface Find<T> {
//
//        Boolean do_(int index, T item) throws Exception;
//    }
//}
