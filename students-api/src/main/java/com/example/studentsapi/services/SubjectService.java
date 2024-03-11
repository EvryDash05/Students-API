package com.example.studentsapi.services;

import com.example.studentsapi.models.request.SubjectRequest;
import com.example.studentsapi.models.response.SubjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<SubjectResponse> retrive(Pageable pageable);

    SubjectResponse saveSubject(SubjectRequest request);

    SubjectResponse findByName(String name);

    SubjectResponse deleteSubjectById(String name);

    SubjectResponse updateSubject(long id, SubjectRequest request);

}
