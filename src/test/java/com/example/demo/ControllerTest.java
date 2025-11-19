package com.example; // MAKE SURE THIS PACKAGE NAME IS CORRECT FOR YOUR PROJECT

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

/**
 * This is an Integration Test that loads the full Spring context
 * and uses the H2 database configured in src/test/resources.
 */
@SpringBootTest 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
class ControllerTest { 

    // Spring injects the Repository bean we need to interact with the database
    @Autowired 
    private StudentRepository studentRepository; 

    @Test
    @Order(1) // Runs first
    void shouldSaveStudent() { 
        // 1. Arrange: Create a new Student object
        Student student = new Student(); 
        student.setName("Charlie"); 
        student.setAddress("Algeria"); 
        
        // 2. Act: Save the student to the H2 database
        studentRepository.save(student); 

        // 3. Assert: Verify the save worked by counting records
        assertThat(studentRepository.count()).isEqualTo(1); // NBR d'etudiant 
    } 

    @Test
    @Order(2) // Runs second (relies on the first test's save)
    void shouldFindAllStudents() { 
        // 1. Act: Fetch all students from the repository 
        List<Student> students = studentRepository.findAll(); 

        // 2. Assert: Verify the count and the data
        assertThat(students).hasSize(1); 
        assertThat(students.get(0).getName()).isEqualTo("Charlie"); 
    } 
}