package com.example.studentsapi.services;


import com.example.studentsapi.documents.Student;
import com.example.studentsapi.models.request.StudentRequest;
import com.example.studentsapi.models.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    List<StudentResponse> retrieve(Pageable pageable);

    StudentResponse save(StudentRequest request);

    StudentResponse findStudentById(Long studentId);

    StudentResponse deleteStudent(long studentId);

    StudentResponse updateStudent(Long id, StudentRequest request);

}
