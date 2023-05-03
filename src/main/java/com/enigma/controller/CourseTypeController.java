package com.enigma.controller;

import com.enigma.model.CourseType;
import com.enigma.model.request.CourseTypeRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CourseTypeService;
import com.enigma.util.UrlMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.COURSE_TYPES)
public class CourseTypeController {
    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseTypeId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        Page<CourseType> result = courseTypeService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course type", result));
    }

    @GetMapping(params = {"typeName"})
     ResponseEntity getAllByName(@Valid @RequestParam("typeName") String name) {
        List<CourseType> courseTypeList = courseTypeService.findAllByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all by name", courseTypeList));
    }

    @PostMapping
    ResponseEntity createCourseType(@Valid @RequestBody CourseTypeRequest courseTypeRequest) {
        CourseType courseType = modelMapper.map(courseTypeRequest, CourseType.class);
        CourseType result = courseTypeService.create(courseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course type", result));
    }
}
