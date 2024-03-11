package com.example.studentsapi.models.response;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {

    private Long subjectId;
    private String name;
    private int duration;

}
