package com.bluementors.training.specifications;

import com.bluementors.training.Skill;
import com.bluementors.training.Training;
import com.bluementors.utils.SpecificationSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TrainingSpecifications {

    private SpecificationSearchCriteria specificationSearchCriteria;

//    public TrainingSpecifications(SpecificationSearchCriteria specificationSearchCriteria) {
//        this.specificationSearchCriteria = specificationSearchCriteria;
//    }

    public static Specification<Training> trainingById(Long trainingId) {
        return new Specification<Training>() {
            @Override
            public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("id"), trainingId);
            }
        };
    }

    public Specification<Training> trainingsForUser(Long userId) {
        return new Specification<Training>() {
            @Override
            public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("id"), userId);
            }
        };
    }

    public Specification<Training> trainingsForUser2(Long userId) {
        return new Specification<Training>() {
            @Override
            public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Root<Skill> skillRoot = criteriaQuery.from(Skill.class);
                return criteriaBuilder.equal(root.get("id"), userId);
            }
        };
    }

//
//        return new Specification<Training>() {
//            @Override
//            public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//
//                switch (specificationSearchCriteria.getOperation()) {
//                    case EQUALITY:
//                        return criteriaBuilder.equal(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue());
//                    case NEGATION:
//                        return criteriaBuilder.notEqual(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue());
//                    case GREATER_THAN:
//                        return criteriaBuilder.greaterThan(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue().toString());
//                    case LESS_THAN:
//                        return criteriaBuilder.lessThan(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue().toString());
//                    case LIKE:
//                        return criteriaBuilder.like(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue().toString());
//                    case CONTAINS:
//                        return criteriaBuilder.like(root.get(specificationSearchCriteria.getKey()), "%" + specificationSearchCriteria.getValue() + "%");
//                    default:
//                        return null;
//                }
//
//                //return criteriaBuilder.equal(root.get(specificationSearchCriteria.getKey()), specificationSearchCriteria.getValue().toString());
//            }
//    }

}
