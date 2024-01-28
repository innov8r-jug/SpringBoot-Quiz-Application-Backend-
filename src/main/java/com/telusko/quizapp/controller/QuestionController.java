package com.telusko.quizapp.controller;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")  //you have to specify the path toh wahi kiya hai
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        System.out.println("getAllQuestions is working");
        return questionService.getAllQuestions();
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<Question>> findByCategory(@RequestParam String category) {
        return questionService.findByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/deleteQuestionById")
    public String deleteById(@RequestParam Integer id) {
        return questionService.deleteById(id);
    }

    @PutMapping("/updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestParam Integer id, @RequestBody Question updatedQuestion) {
//     isme question body wale part mein apan type karenge pehle, and then wo service mein jaakr same id pe
//     agar koi question exist karta hai to usko update kar dega setters use karke,
//     isiliye yaha jo apan update question pass kar rhe hai wo apni side se hai, to service
//     service apne aap question dhoondega using find by id and update kr dega
        return questionService.updateQuestion(id, updatedQuestion);
    }
}




