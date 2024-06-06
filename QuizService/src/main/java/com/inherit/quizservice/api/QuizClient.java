package com.inherit.quizservice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("QUESTIONSERVICE")
public interface QuizClient {

    @GetMapping("questions/quiz")
    ResponseEntity<List<Integer>> quizSession(@RequestParam String cat, @RequestParam int numOfQs);

    @PostMapping("questions/{id}")
    ResponseEntity<List<Map<String, String>>> getQuestionsById(@PathVariable int[] id);

    @PostMapping("questions/score")
    int getScore(@RequestBody Map<Integer, String> responses);
}
//Load Balancing will happen automatically