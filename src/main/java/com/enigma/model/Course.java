package com.enigma.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_course")
public class Course {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id", referencedColumnName = "course_info_id")
    private CourseInfo courseInfo;

    @ManyToOne
    @JoinColumn(name = "course_type_id", referencedColumnName = "course_type_id", nullable = false)
    private CourseType courseType;

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public Course setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
        return this;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public Course setCourseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public Course setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Course setLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", courseType=" + courseType +
                ", courseInfo=" + courseInfo +
                '}';
    }
}
