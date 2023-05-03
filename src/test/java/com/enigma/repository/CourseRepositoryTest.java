package com.enigma.repository;

import com.enigma.model.Course;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.repository.spec.Spec;
import com.enigma.util.QueryOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(value = {"classpath:course.sql"})
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    void findByTitleContains() {
        List<Course> courses = courseRepository.findByTitleContains("title");

        assertEquals(2, courses.size());
    }

    @Test
    void findByDescriptionContains() {
        List<Course> courses = courseRepository.findByDescriptionContains("description");

        assertEquals(2, courses.size());
    }

    @Test
    void findAll() {
        SearchCriteria searchCriteria = new SearchCriteria("title", QueryOperator.LIKE, "title");
        Specification specification = new Spec<Course>().findBy(searchCriteria);
        List<Course> courses = courseRepository.findAll(specification);

        assertEquals(2, courses.size());
    }
}
