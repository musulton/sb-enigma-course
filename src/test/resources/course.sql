INSERT INTO m_course_info(course_info_id, duration, level) VALUES ('1', 'duration 1', 'level 1');

INSERT INTO m_course_type(course_type_id, type_name) VALUES ('1', 'type name 1');

INSERT INTO m_course(course_id, title, description, link, course_info_id, course_type_id) VALUES ('1', 'dummy title 1', 'description 1', 'link 1', '1', '1');

INSERT INTO m_course(course_id, title, description, link, course_info_id, course_type_id) VALUES ('2', 'dummy title 2', 'description 2', 'link 2', '1', '1');
