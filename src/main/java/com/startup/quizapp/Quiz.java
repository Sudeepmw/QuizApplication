package com.startup.quizapp;


import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String title;
    @ManyToMany
    List<Question> questions;

}
