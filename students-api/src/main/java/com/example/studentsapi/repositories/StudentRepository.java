package com.example.studentsapi.repositories;

import com.example.studentsapi.documents.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

    public Optional<Student> findStudentByName(String name);

}
