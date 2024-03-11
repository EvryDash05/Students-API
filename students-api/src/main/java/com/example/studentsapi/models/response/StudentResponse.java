package com.example.studentsapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long studentId;
    private String name;
    private String email;
    private String phone;
    private int age;
    private Set<String> subjects;

}
