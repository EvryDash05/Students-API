package com.example.studentsapi.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subjects")
public class Subject implements Serializable {

    @Id
    private Long subjectId;
    private String name;
    private int duration;

}
