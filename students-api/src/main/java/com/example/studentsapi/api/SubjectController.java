package com.example.studentsapi.api;

import com.example.studentsapi.bussines.SubjectBussines;
import com.example.studentsapi.models.request.SubjectRequest;
import com.example.studentsapi.models.response.SubjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectBussines subjectBussines;

    @GetMapping("/listSubjects/{page}")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects(@PageableDefault Pageable page){
        return ResponseEntity.ok(this.subjectBussines.retrive(page));
    }

    @GetMapping("/findSubjectByName/{name}")
    public ResponseEntity<SubjectResponse> getSubjectByName(@PathVariable String name){
        return ResponseEntity.ok(this.subjectBussines.findByName(name));
    }

    /*@GetMapping("/findById/{id}")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable long id){
        return ResponseEntity.ok(this.getSubjectById(id));
    }*/

    @PostMapping("/registerSubject")
    public ResponseEntity<?> registerSubject(@RequestBody SubjectRequest request){
        return new ResponseEntity<>(this.subjectBussines.saveSubject(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable long id, @RequestBody SubjectRequest request){
        return ResponseEntity.ok(this.subjectBussines.updateSubject(id, request));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteSubject(@PathVariable String name){
        return ResponseEntity.ok(this.subjectBussines.deleteSubjectById(name));
    }

}
