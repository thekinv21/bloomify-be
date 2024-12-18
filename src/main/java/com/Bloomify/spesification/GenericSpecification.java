package com.Bloomify.spesification;

import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;

public class GenericSpecification<T> {

    public Specification<T> searchBy(
            List<String> fieldNames, String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(searchTerm) && fieldNames != null && !fieldNames.isEmpty()) {
                String likeTerm = "%" + searchTerm.toLowerCase() + "%";

                List<Predicate> fieldPredicates =
                        new ArrayList<>(
                                fieldNames.stream()
                                        .map(
                                                fieldName ->
                                                        criteriaBuilder.like(
                                                                criteriaBuilder.lower(root.get(fieldName).as(String.class)), likeTerm))
                                        .toList());
                predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
