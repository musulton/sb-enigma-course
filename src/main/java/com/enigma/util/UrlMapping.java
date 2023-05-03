package com.enigma.util;

public abstract class UrlMapping {
    public final static String AUTH = "/auth";
    public final static String REGISTER = "/register";
    public final static String LOGIN = "/login";

    public final static String COURSES = "/courses";
    public final static String COURSE_TYPES = "/course-types";
    public final static String COURSE_INFO = "/course-info";
    public final static String COURSE_PAYMENT = "/course-payment";
    public final static String COURSE_FILES = "/course-files";
    public final static String COURSE_FILES_IMAGE = "/images/{name}";
    public final static String USERS = "/users";
}
