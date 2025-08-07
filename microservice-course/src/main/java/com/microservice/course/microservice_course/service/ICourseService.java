package com.microservice.course.microservice_course.service;

import com.microservice.course.microservice_course.entities.Course;
import java.util.List;
import com.microservice.course.microservice_course.http.response.StudentByCourseResponse;
public interface ICourseService {

    List<Course> findAll();

    Course findById(Long id);

    void save(Course course);

    StudentByCourseResponse findStudentByCourseId(Long idCourse);


}
