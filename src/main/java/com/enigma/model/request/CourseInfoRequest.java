package com.enigma.model.request;

import javax.validation.constraints.NotBlank;

public class CourseInfoRequest {

    @NotBlank(message = "{invalid.duration.required}")
    private String duration;

    @NotBlank(message = "{invalid.level.required}")
    private String level;

    @Override
    public String toString() {
        return "CourseInfoRequest{" +
                "duration='" + duration + '\'' +
                ", level='" + level + '\'' +
                '}';
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
