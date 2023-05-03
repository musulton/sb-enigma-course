package com.enigma.repository.spec;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class Spec<T> {

    /**
     * For join course and course type table
     */
    public Specification<T> courseTypeId(String courseTypeId) {
        return ((root, query, criteriaBuilder) -> {
            Join<T, CourseType> courseTypeJoin = root.join("courseType");
            return criteriaBuilder.equal(courseTypeJoin.get("courseTypeId"), courseTypeId);
        });
    }

    public Specification<T> findBy(SearchCriteria searchCriteria) {
        switch (searchCriteria.getOperator()) {
            case LIKE:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"));
            case EQUAL:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            case NOT_EQUAL:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            default:
                throw new RuntimeException("Operator not supported");
        }
    };
}
