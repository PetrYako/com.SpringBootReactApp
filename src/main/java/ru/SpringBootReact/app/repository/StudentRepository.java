package ru.SpringBootReact.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.SpringBootReact.app.model.Student;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> selectAllStudents() {
        String sql = "" +
                "SELECT * " +
                "FROM student";

        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    public int insertNewStudent(Student student) {
        String sql = "" +
                " INSERT INTO student" +
                " (student_id, first_name, last_name, email, gender)" +
                " VALUES (?, ?, ?, ?, ?::gender)";

        return jdbcTemplate.update(
                sql,
                UUID.randomUUID(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );
    }

    public int deleteStudent(UUID studentId) {
        String sql = "" +
                "DELETE FROM student " +
                "WHERE student.student_id = ?";

        return jdbcTemplate.update(sql, studentId);
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> new Student(
                UUID.fromString(resultSet.getString("student_id")),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                Student.Gender.valueOf(resultSet.getString("gender").toUpperCase())
        );
    }


    @SuppressWarnings("ConstantConditions")
    public boolean checkEmail(String email) {
        String sql = "" +
                "SELECT EXISTS (" +
                " SELECT 1" +
                " FROM student" +
                " WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {email},
                ((resultSet, i) -> resultSet.getBoolean(1))
        );
    }

    @SuppressWarnings("ConstantConditions")
    public boolean checkId(UUID studentId) {
        String sql = "" +
                "SELECT EXISTS(" +
                " SELECT 1" +
                " FROM student" +
                " WHERE student_id = ?" +
                ")";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {studentId},
                (((resultSet, i) -> resultSet.getBoolean(1)))
        );
    }


}
