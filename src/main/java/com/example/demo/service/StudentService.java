package com.example.demo.service;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentService {
@Autowired
private StudentRepository StudentRepository;
public Student saveStudent(Student student) {
return StudentRepository.save(student);
}
public List<Student> getAllStudents() {
return StudentRepository.findAll();
}
} 