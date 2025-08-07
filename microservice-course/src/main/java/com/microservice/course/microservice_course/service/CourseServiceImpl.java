package com.microservice.course.microservice_course.service;

import com.microservice.course.microservice_course.client.StudentClient;
import com.microservice.course.microservice_course.dto.StudentDTO;
import com.microservice.course.microservice_course.entities.Course;
import com.microservice.course.microservice_course.http.response.StudentByCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.course.microservice_course.persistence.ICoursesRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private ICoursesRepository coursesRepository;

    @Autowired
    private StudentClient studentClient;
    
    @Override
    public List<Course> findAll() {
        return (List<Course>) coursesRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return coursesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        coursesRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentByCourseId(Long idCourse) {
        //Consultar el curso
        Course course = coursesRepository.findById(idCourse).orElseThrow(() ->
            new RuntimeException("Curso no encontrado con ID: " + idCourse));

        //Obtener los estudiantes del curso
        List<StudentDTO> students = studentClient.findAllStudentsByCourseId(idCourse);

        return StudentByCourseResponse.builder().
                courseName(course.getName()).teacher(course.getTeacher()).studentDTOList(students).build();
    }
}
