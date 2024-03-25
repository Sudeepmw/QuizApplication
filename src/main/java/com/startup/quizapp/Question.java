package com.startup.quizapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Entity
@Data
@Getter
@Setter
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String questions;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer rightAnswer;
    private String difficultyLevel;
}
