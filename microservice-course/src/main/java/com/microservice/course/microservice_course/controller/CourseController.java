package com.microservice.course.microservice_course.controller;

import com.microservice.course.microservice_course.service.ICourseService;
import com.microservice.course.microservice_course.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }
    @GetMapping("/search-student/{idCourse}")
    public ResponseEntity<?> findAllStudentByCourseId(@PathVariable Long idCourse) {
        return ResponseEntity.ok(courseService.findStudentByCourseId(idCourse));
    }
}
