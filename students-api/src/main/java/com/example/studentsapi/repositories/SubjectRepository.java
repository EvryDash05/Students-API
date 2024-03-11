package com.example.studentsapi.repositories;

import com.example.studentsapi.documents.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, Long> {

    @Query("{ name : ?0 }")
    Optional<Subject> findSubjectByName(String name);
}
