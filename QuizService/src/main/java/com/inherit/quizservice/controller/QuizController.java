package com.inherit.quizservice.controller;

import com.inherit.quizservice.api.QuizClient;
import com.inherit.quizservice.model.Quiz;
import com.inherit.quizservice.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController @RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizClient quizClient;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String cat, @RequestParam int numOfQs, @RequestParam String title) {
        var questions = quizClient.quizSession(cat, numOfQs).getBody();
        return new ResponseEntity<>(quizRepo.save(new Quiz(title, questions)), HttpStatus.CREATED);
    }

    @GetMapping("newq/{quizId}")
    public List<Map<String, String>> getQuestion(@PathVariable int quizId) {
        int[] ids = quizRepo.findById(quizId).get().getQuestions().stream().mapToInt(i->i).toArray();
        return quizClient.getQuestionsById(ids).getBody();
    }

    @PostMapping("submit")
    public int createQuiz(@RequestBody Map<Integer, String> responses) {
        return quizClient.getScore(responses);
    }


}
