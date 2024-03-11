package com.example.studentsapi.models.request;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private Long studentId;
    private String name;
    @Email
    private String email;
    private String phone;
    private int age;
    private Set<String> subjects;

}
