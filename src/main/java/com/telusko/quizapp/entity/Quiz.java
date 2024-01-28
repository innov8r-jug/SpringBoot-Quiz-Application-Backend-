package com.telusko.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data   // form lombock to have getter and setter
public class Quiz
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    //here we have to do mapping because we have a quiz with multiple question , and different quizes can have same questions as well
    @ManyToMany
    private List<Question> questions;
}
