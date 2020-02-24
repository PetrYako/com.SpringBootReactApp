package ru.SpringBootReact.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.SpringBootReact.app.EmailValidator;
import ru.SpringBootReact.app.exceptions.AppRequestException;
import ru.SpringBootReact.app.model.Student;
import ru.SpringBootReact.app.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          EmailValidator emailValidator) {
        this.studentRepository = studentRepository;
        this.emailValidator = emailValidator;
    }

    public List<Student> getAllStudents() {
        return studentRepository.selectAllStudents();
    }

    public void createNewStudent(Student student) {
        String email = student.getEmail();

        if (!emailValidator.test(email)) {
            throw new AppRequestException(email + " is invalid");
        }

        if (studentRepository.checkEmail(email)) {
            throw new AppRequestException(email + " is taken");
        }

        studentRepository.insertNewStudent(student);
    }

    public void deleteStudent(UUID studentId) {
        if (studentRepository.checkId(studentId)) {
            throw new AppRequestException(studentId + " doesn't exists");
        }

        studentRepository.deleteStudent(studentId);
    }
}
