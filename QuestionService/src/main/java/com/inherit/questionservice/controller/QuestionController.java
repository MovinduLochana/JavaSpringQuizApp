package com.inherit.questionservice.controller;

import com.inherit.questionservice.model.Question;
import com.inherit.questionservice.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController @RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionRepo questionRepo;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("{cat}")
    public ResponseEntity<List<Question>> questionsByCat(@PathVariable String cat) {
        return new ResponseEntity<>(questionRepo.findByCategory(cat), HttpStatus.FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<Question> getAllQuestions(@RequestBody Question question) {
        return new ResponseEntity<>(questionRepo.save(question), HttpStatus.CREATED);
    }

    @GetMapping("quiz")
    public ResponseEntity<List<Integer>> quizSession(@RequestParam String cat, @RequestParam int numOfQs){
        return new ResponseEntity<>(questionRepo.findRandomQuestionsId(cat, numOfQs), HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<List<Map<String, String>>> getQuestionsById(@PathVariable int[] id) {

        List<Map<String, String>> ls = new ArrayList<>();

        for (int i : id) {
            var q = questionRepo.findById(i).get();
            var question = Map.of("title", q.getTitle(), "op1", q.getOp1(), "op2", q.getOp2(), "op3", q.getOp3(), "op4", q.getOp4());
            ls.add(question);
        }

        return new ResponseEntity<>(ls, HttpStatus.OK);
    }

    @PostMapping("score")
    public int getScore(@RequestBody Map<Integer, String> responses) {
        return (int) responses.entrySet().stream().filter(i -> i.getValue().equals(questionRepo.findAnsById(i.getKey()))).count();
    }


}
/* Check Load Balancing by feignClient(when have multiple instance running[interllij config])(remove server.port on prop)
*  Environment env (AutoWired)
*  env.getProperty("local.server.port")
* */
/* *

return Arrays.stream(id).mapToObj(i -> questionRepo.findById(i).get())
.map(q -> Map.of("title", q.getTitle(), "op1", q.getOp1(), "op2", q.getOp2(), "op3", q.getOp3(), "op4", q.getOp4()))
.collect(Collectors.toList());

Arrays.stream(id).forEach(i -> {
            var q = questionRepo.findById(i).get();
            var question = Map.of("title", q.getTitle(),"op1", q.getOp1(), "op2", q.getOp2(), "op3", q.getOp3(), "op4", q.getOp4());
            ls.add(question);
        });
        responses.forEach((index, val) -> {
            questionRepo.findById(index).get().getAns().matches(val);
        });

        //for(int i : id) questions.add(questionRepo.findById(i).orElse(null));
        //Arrays.stream(id).forEach(i -> questions.add(questionRepo.findById(i).orElse(null).wrap()));
----------------------------------------------------------------------------------------------------------------
Map<String, String> question = new HashMap<>(){{
                put("title", q.getTitle());
                put("op1", q.getOp1());
                put("op2", q.getOp2());
                put("op3", q.getOp3());
                put("op4", q.getOp4());
            }};
* */