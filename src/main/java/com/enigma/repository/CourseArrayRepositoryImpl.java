package com.enigma.repository;

import com.enigma.model.Course;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseArrayRepositoryImpl implements CourseArrayRepository {

    @Autowired
    RandomStringGenerator randomStringGenerator;

    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for(Course course: courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    void addToBucket(String keyword, String value, List<Course> bucket, Course course) {
        if (keyword.toLowerCase().contains(value)) {
            bucket.add(course);
        }
    }

    @Override
    public Optional<List<Course>> findBy(String key, String val) {
        List<Course> result = new ArrayList<>();
        for (Course course: courses) {
            switch (key) {
                case "title":
                    addToBucket(course.getTitle(), val.toLowerCase(), result, course);
                    break;
                case "description":
                    addToBucket(course.getDescription(), val.toLowerCase(), result, course);
                    break;
                case "link":
                    addToBucket(course.getLink(), val.toLowerCase(), result, course);
                    break;
            }
        }

        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for(Course existingCourse: courses) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course: courses) {
            if (course.getCourseId().equals(id)) {
                courses.remove(course);
                break;
            }
        }
    }
}
