package com.microservice.course.microservice_course.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.microservice.course.microservice_course.entities.Course;

@Repository
public interface ICoursesRepository extends CrudRepository<Course, Long> {
}
