package com.inherit.questionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data @Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String op1, op2, op3, op4;
    private String ans;
    private String diffLevel;
    private String category;

}

/*
*
* //    public Question wrap() {
//        return new Question(this.title,this.op1,this.op2,this.op3,this.op4);
//    }
* NonNull ==> RequiredArgConstructor
*
*
* */
