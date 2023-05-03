package com.enigma.repository;

import com.enigma.model.CourseType;
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
class CourseTypeRepositoryTest {

    @Autowired
    CourseTypeRepository courseTypeRepository;

    @Test
    void findAll() {
        SearchCriteria searchCriteria = new SearchCriteria("typeName", QueryOperator.EQUAL, "type name 1");
        Specification specification = new Spec<CourseType>().findBy(searchCriteria);

        List<CourseType> courseTypes = courseTypeRepository.findAll(specification);

        assertEquals(1, courseTypes.size());
    }
}
