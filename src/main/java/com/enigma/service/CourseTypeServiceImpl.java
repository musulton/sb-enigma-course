package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.model.CourseType;
import com.enigma.repository.CourseTypeRepository;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.repository.spec.Spec;
import com.enigma.util.QueryOperator;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Autowired
    RandomStringGenerator randomStringGenerator;

    CourseTypeRepository courseTypeRepository;

    public CourseTypeServiceImpl(@Autowired CourseTypeRepository courseTypeRepository) {
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public Page<CourseType> list(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<CourseType> courses = courseTypeRepository.findAll(pageable);
        return courses;
    }

    @Override
    public CourseType create(CourseType courseType) {
        try {
            courseType.setCourseTypeId(randomStringGenerator.random());
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public List<CourseType> findAllByName(String value) {
        SearchCriteria searchCriteria = new SearchCriteria()
                .setOperator(QueryOperator.LIKE)
                .setKey("typeName")
                .setValue(value);
        Specification spec = new Spec<CourseType>().findBy(searchCriteria);
        List<CourseType> courseTypes = courseTypeRepository.findAll(spec);
        return courseTypes;
    }
}
