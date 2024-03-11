package com.example.studentsapi.api;

import com.example.studentsapi.bussines.StudentBussines;
import com.example.studentsapi.models.request.StudentRequest;
import com.example.studentsapi.models.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentBussines studentBussines;

    @GetMapping("/listStudents/{page}")
    public ResponseEntity<List<StudentResponse>> getAllStudents(@PageableDefault Pageable page) {
        return ResponseEntity.ok(this.studentBussines.retrieve(page));
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRequest request){
        return new ResponseEntity<>(this.studentBussines.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId){
        return new ResponseEntity<>(this.studentBussines.findStudentById(studentId), HttpStatus.FOUND);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long studentId, @RequestBody StudentRequest request){
        return new ResponseEntity<>(this.studentBussines.updateStudent(studentId, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(this.studentBussines.deleteStudent(studentId));
    }

}
