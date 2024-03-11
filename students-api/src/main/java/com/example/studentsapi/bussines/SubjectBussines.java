package com.example.studentsapi.bussines;

import com.example.studentsapi.constants.ErrorConstants;
import com.example.studentsapi.documents.Subject;
import com.example.studentsapi.exceptions.BussinesException;
import com.example.studentsapi.models.request.SubjectRequest;
import com.example.studentsapi.models.response.SubjectResponse;
import com.example.studentsapi.repositories.SubjectRepository;
import com.example.studentsapi.services.SubjectService;
import com.example.studentsapi.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectBussines implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> retrive(Pageable pageable) {
        int currentPage = Utilities.getCurrentPage(pageable);
        log.info(String.valueOf(currentPage));
        return this.subjectRepository
                .findAll(PageRequest.of(currentPage, pageable.getPageSize()))
                .map(this::toSubjectResponse)
                .toList();
    }

    @Override
    public SubjectResponse saveSubject(SubjectRequest request) {
        Optional<Subject> findSubject = subjectRepository.findSubjectByName(request.getName());
        if (findSubject.isPresent()) {
            throw new BussinesException(HttpStatus.FOUND.value(), ErrorConstants.SUBJECT_EXIST);
        } else {
            Subject newSubject = subjectRepository.save(this.toSubjectDocument(request));
            return toSubjectResponse(newSubject);
        }
    }

    @Override
    public SubjectResponse findByName(String name) {
        Optional<Subject> findSubject = this.subjectRepository.findSubjectByName(name);
        if (findSubject.isPresent()) {
            return this.toSubjectResponse(findSubject.get());
        } else {
            throw new BussinesException(HttpStatus.NOT_FOUND.value(), ErrorConstants.SUBJECT_NOT_FOUND);
        }
    }

    @Override
    public SubjectResponse updateSubject(long id, SubjectRequest request) {
        Optional<Subject> findSubject = this.subjectRepository.findById(id);
        if (findSubject.isPresent()) {
            Subject updateSubject = Subject.builder()
                    .subjectId(findSubject.get().getSubjectId())
                    .name(request.getName())
                    .duration(request.getDuration())
                    .build();

            this.subjectRepository.save(updateSubject);

            return this.toSubjectResponse(updateSubject);
        } else {
            throw new BussinesException(HttpStatus.CONFLICT.value(), ErrorConstants.SUBJECT_NOT_FOUND);
        }
    }


    @Override
    public SubjectResponse deleteSubjectById(String name) {
        Optional<Subject> findSubject = this.subjectRepository.findSubjectByName(name);
        if (findSubject.isPresent()) {
            this.subjectRepository.delete(findSubject.get());
            return toSubjectResponse(findSubject.get());
        } else {
            throw new BussinesException(HttpStatus.CONFLICT.value(), ErrorConstants.SUBJECT_NOT_FOUND);
        }
    }

    /* <----- Mappers -----> */
    private SubjectResponse toSubjectResponse(Subject subject) {
        return new ModelMapper().map(subject, SubjectResponse.class);
    }

    private Subject toSubjectDocument(SubjectRequest request) {
        return new ModelMapper().map(request, Subject.class);
    }

}
