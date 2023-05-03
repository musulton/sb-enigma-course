package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.service.CourseService;
import com.enigma.util.QueryOperator;
import com.enigma.util.UrlMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.COURSES)
@Validated
public class CourseController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourse(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "courseId") String sortBy
    ) throws Exception {
        Page<Course> courses = courseService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get course list", courses));
    }

    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getAllBy(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("operator") String operator) {
        SearchCriteria searchCriteria = new SearchCriteria()
                .setKey(key)
                .setValue(value)
                .setOperator(QueryOperator.valueOf(operator));
        List<Course> courses = courseService.list(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all course by", courses));
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid CourseRequest course) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Course result = courseService.create(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception {
        Course course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course with id", course));
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam @NotBlank(message = "{invalid.keyword.required}") String keyword, @RequestParam String value) throws Exception {
        List<Course> result = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by " + keyword, result));
        }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody CourseRequest courseRequest, @PathVariable("id") String id) throws Exception {
        courseService.update(courseRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update course", courseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) throws Exception {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete course", null));
    }
}
