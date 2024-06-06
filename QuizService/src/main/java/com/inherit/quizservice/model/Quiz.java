package com.inherit.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Entity @NoArgsConstructor
public class Quiz {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ElementCollection
    private List<Integer> questions;

    public Quiz(String title, List<Integer> questions) {
        this.title = title;
        this.questions = questions;
    }
}
