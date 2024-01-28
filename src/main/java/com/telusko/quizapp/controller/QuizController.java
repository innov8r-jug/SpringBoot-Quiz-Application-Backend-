package com.telusko.quizapp.controller;

import com.telusko.quizapp.entity.Response;
import com.telusko.quizapp.entity.WrappedQuestion;
import com.telusko.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<String>createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        return quizService.createQuiz(category, numQ, title) ;
    }

    @GetMapping("/get")
    public ResponseEntity<List<WrappedQuestion>>getQuizQuestions(@RequestParam Integer id) //isme id quiz ki id rahegi, jitni quizzes banadi
    {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit")                                //yaha pe id means quiz ki id hi hai
    public ResponseEntity<Integer> submitQuiz(@RequestParam Integer id, @RequestBody List<Response> responses)
                                                                      //responses likhte time postman pe id bhi deni padegi, response entity
                                                                      //mein private id, and private string response aata hai
    {
        return quizService.calculateResult(id,responses);
    }
}
