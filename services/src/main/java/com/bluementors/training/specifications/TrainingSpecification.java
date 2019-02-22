package com.bluementors.training.specifications;

import com.bluementors.training.Training;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TrainingSpecification implements Specification<Training> {
    @Override
    public Specification<Training> and(Specification<Training> other) {
        return null;
    }

    @Override
    public Specification<Training> or(Specification<Training> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
