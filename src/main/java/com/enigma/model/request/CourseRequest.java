package com.enigma.model.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class CourseRequest {

    @NotBlank(message = "{invalid.title.required}")
    private String title;

    private String description;

    private MultipartFile file;

    @NotBlank(message = "Type is required")
    private String courseTypeId;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "Level is required")
    private String level;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
