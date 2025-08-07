package com.microservice.course.microservice_course.client;

import com.microservice.course.microservice_course.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "microservice-student", url = "localhost:8090/api/students/")
public interface StudentClient {

    @GetMapping("/search-my-course/{idCourse}")
    List<StudentDTO> findAllStudentsByCourseId(@PathVariable Long idCourse);
}
