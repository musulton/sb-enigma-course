package com.enigma.service;

import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.repository.spec.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<Course> list() throws Exception;
    Course create(CourseRequest course) throws Exception;
    Course get(String id) throws Exception;
    void update(CourseRequest course, String id) throws Exception;
    void delete(String id) throws Exception;

    List<Course> getBy(String keyword, String value) throws Exception;

    Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

    List<Course> list(SearchCriteria searchCriteria);
}
