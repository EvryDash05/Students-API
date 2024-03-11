package com.example.studentsapi.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {

    @Id
    private Long studentId;
    private String name;
    private String email;
    private String phone;
    private int age;

    @DBRef
    private Set<Subject> subjects;

}
