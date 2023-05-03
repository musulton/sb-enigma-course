package com.enigma.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_info_id")
    private String courseInfoId;

    @Column(name = "duration", length = 50, nullable = false)
    private String duration;

    @Column(name = "level", length = 50, nullable = false)
    private String level;

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseInfoId='" + courseInfoId + '\'' +
                ", duration='" + duration + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public String getCourseInfoId() {
        return courseInfoId;
    }

    public CourseInfo setCourseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public CourseInfo setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public CourseInfo setLevel(String level) {
        this.level = level;
        return this;
    }
}
