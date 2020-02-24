package ru.SpringBootReact.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.SpringBootReact.app.model.Student;
import ru.SpringBootReact.app.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void createNewStudent(@RequestBody @Valid Student student) {
        studentService.createNewStudent(student);
    }

    @DeleteMapping
    public void deleteStudent(UUID studentId) {
        studentService.deleteStudent(studentId);
    }
}
