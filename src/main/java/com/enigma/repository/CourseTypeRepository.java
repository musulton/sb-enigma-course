package com.enigma.repository;

import com.enigma.model.CourseType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeRepository extends JpaRepository<CourseType, String> {
    List<CourseType> findAll(Specification specification);
}
