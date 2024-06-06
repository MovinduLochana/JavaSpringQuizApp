package com.inherit.questionservice.repo;

import com.inherit.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value="select q.id from question q where q.category =:category order by rand() limit :numOfQs", nativeQuery = true)
    List<Integer> findRandomQuestionsId(String category, int numOfQs);

    @Query(value = "select q.ans from question q where q.id= :i" , nativeQuery = true)
    String findAnsById(int i);
}

//Question findQuestionById(int id);
