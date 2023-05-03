package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.model.CourseInfo;
import com.enigma.model.CourseType;
import com.enigma.model.request.CourseRequest;
import com.enigma.repository.CourseRepository;
import com.enigma.repository.CourseTypeRepository;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.repository.spec.Spec;
import org.modelmapper.ModelMapper;
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
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private CourseTypeRepository courseTypeRepository;

    private CourseUploadService courseUploadService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseTypeRepository courseTypeRepository, CourseUploadService courseUploadService) {
        this.courseRepository = courseRepository;
        this.courseTypeRepository = courseTypeRepository;
        this.courseUploadService = courseUploadService;
    }

    @Override
    public List<Course> list() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public Course create(CourseRequest courseRequest) {
        try {
            String filePath = "";
            Optional<CourseType> courseType = courseTypeRepository.findById(courseRequest.getCourseTypeId());

            if (courseType.isEmpty()) {
                throw new NotFoundException("Course type is not found");
            }

            if (!courseRequest.getFile().isEmpty()) {
                filePath = courseUploadService.uploadMaterial(courseRequest.getFile());
            }

            CourseInfo courseInfo = new CourseInfo()
                    .setDuration(courseRequest.getDuration())
                    .setLevel(courseRequest.getLevel());
            Course course = new Course()
                    .setTitle(courseRequest.getTitle())
                    .setDescription(courseRequest.getDescription())
                    .setLink(filePath)
                    .setCourseType(courseType.get())
                    .setCourseInfo(courseInfo);
            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return course.get();
    }

    @Override
    public void update(CourseRequest courseRequest, String id) {
        try {
            Course existingCourse = get(id);
            existingCourse.setTitle(courseRequest.getTitle());
            existingCourse.setDescription(courseRequest.getDescription());

            CourseInfo courseInfo = new CourseInfo()
                    .setDuration(courseRequest.getDuration())
                    .setLevel(courseRequest.getLevel());
            existingCourse.setCourseInfo(courseInfo);

            courseRepository.save(existingCourse);
        } catch (NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }

    @Override
    public void delete(String id) {
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }

    List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }

        return courses;
    }

    List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " description is not found");
        }

        return courses;
    }

    public List<Course> getBy(String key, String val) {
        switch (key) {
            case "title":
                return findByTitleContains(val);
            case "description":
                return findByDescriptionContains(val);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public Page<Course> list(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }

    @Override
    public List<Course> list(SearchCriteria searchCriteria) {
        Specification spec = new Spec<Course>().findBy(searchCriteria);
        List<Course> courses = courseRepository.findAll(spec);
        return courses;
    }
}
