package com.example.studentsapi.models.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest implements Serializable {

    private Long subjectId;
    private String name;
    private int duration;

}
