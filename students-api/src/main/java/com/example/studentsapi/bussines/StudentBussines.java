package com.example.studentsapi.bussines;

import com.example.studentsapi.constants.ErrorConstants;
import com.example.studentsapi.documents.Student;
import com.example.studentsapi.documents.Subject;
import com.example.studentsapi.exceptions.BussinesException;
import com.example.studentsapi.models.request.StudentRequest;
import com.example.studentsapi.models.response.StudentResponse;
import com.example.studentsapi.repositories.StudentRepository;
import com.example.studentsapi.repositories.SubjectRepository;
import com.example.studentsapi.services.StudentService;
import com.example.studentsapi.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentBussines implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    //Optimizar el código de esta función
    @Override
    public List<StudentResponse> retrieve(Pageable pageable) {
        int currentPage = Utilities.getCurrentPage(pageable);
        List<Student> findStudents = this.studentRepository.findAll(PageRequest.of(currentPage, pageable.getPageSize()))
                .stream().toList();
        List<StudentResponse> students = new ArrayList<>();
        findStudents.forEach(student -> {
            Set<String> subjects = student.getSubjects().stream()
                    .map(subject -> (subject.getName()))
                    .collect(Collectors.toSet());
            StudentResponse response = this.studentToResponse(student);
            response.setSubjects(subjects);
            students.add(response);
        });
        /*return this.studentRepository.findAll(PageRequest.of(currentPage, pageable.getPageSize()))
                .stream().map(this::studentToResponse)
                .toList();*/
        return students;
    }

    @Override
    public StudentResponse save(StudentRequest request) {
        Optional<Student> findStudent = this.studentRepository.findStudentByName(request.getName());
        if (findStudent.isPresent()) {
            throw new BussinesException(HttpStatus.CONFLICT.value(), ErrorConstants.STUDENT_EXIST);
        } else {
            Set<Subject> subjects = request.getSubjects().stream()
                    .map(name -> subjectRepository.findSubjectByName(name).get())
                    .collect(Collectors.toSet());

            Student student = requestToStudent(request);
            student.setSubjects(subjects);

            this.studentRepository.save(student);

            return studentToResponse(student);
        }
    }

    @Override
    public StudentResponse findStudentById(Long studentId) {
        Optional<Student> student = this.studentRepository.findById(studentId);
        if (student.isPresent()) {
            return this.studentToResponse(student.get());
        } else {
            throw new BussinesException(HttpStatus.NOT_FOUND.value(), ErrorConstants.STUDENT_NOT_FOUND);
        }
    }

    @Override
    public StudentResponse deleteStudent(long studentId){
        Optional<Student> findStudent = this.studentRepository.findById(studentId);
        if (findStudent.isPresent()) {
            this.studentRepository.deleteById(findStudent.get().getStudentId());
            return this.studentToResponse(findStudent.get());
        } else {
            throw new BussinesException(HttpStatus.NOT_FOUND.value(), ErrorConstants.STUDENT_NOT_FOUND);
        }
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Optional<Student> findStudent = this.studentRepository.findById(id);
        if (findStudent.isPresent()) {

            Set<Subject> subjects = new HashSet<>();
            request.getSubjects().forEach(subjectName -> {
                    Optional<Subject> optionalSubject = this.subjectRepository.findSubjectByName(subjectName);
                    optionalSubject.ifPresent(subjects::add);
            });

            Student updateStudent = Student.builder()
                    .studentId(request.getStudentId())
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .age(request.getAge())
                    .subjects(subjects)
                    .build();

            this.studentRepository.save(updateStudent);

            return this.studentToResponse(updateStudent);
        } else {
            throw new BussinesException(HttpStatus.NOT_FOUND.value(), ErrorConstants.STUDENT_NOT_FOUND);
        }
    }

    /* <----- Mappers -----> */
    private StudentResponse studentResponseMap(StudentRequest request) {
        return new ModelMapper().map(request, StudentResponse.class);
    }

    private Student requestToStudent(StudentRequest request) {
        return new ModelMapper().map(request, Student.class);
    }

    private StudentResponse studentToResponse(Student student) {
        return new ModelMapper().map(student, StudentResponse.class);
    }

}
