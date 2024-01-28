package com.telusko.quizapp.service;


import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    QuestionDao questiondao;
    //ResponseEntity<> helps us to get the result and Http status and DONT FORGET WRITING 'new' keyword
    public ResponseEntity<List<Question>> getAllQuestions() {
        try
        {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if something goes wrong you can return an empty array and a bad request http status
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findByCategory(String category)
    {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //if something goes wrong you can return an empty array and a bad request http status
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question)
    {
        questiondao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
        //this CREATED will give us a Http status of 201
        //exception handling can be done using try catch throw and responding with a message that Question was not added
    }

    public String deleteById(Integer id)
    {
        questiondao.deleteById(id);
        return "Question Deleted";
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        //check if the question with the given id exists
        if(!questiondao.existsById(id)) //if no such id exists, that has been passed by controller then
        {
            return ResponseEntity.notFound().build();
            //In Spring Framework, ResponseEntity is a class that represents the entire HTTP response,
            //It allows you to build and customize the response that your controller method sends back to the client.
            //notFound(): This is a static method of the ResponseEntity class. It is used to create an instance of
            //ResponseEntity with the HTTP status set to 404 Not Found. The 404 status code is commonly used to indicate
            //that the requested resource could not be found on the server.
            //build(): This method is used to build the final ResponseEntity instance. It essentially completes the
            //creation of the response entity. In this case, it signifies that the response doesn't have a specific body
        }
        //get the existing question from the database
        Question existingQuestion = questiondao.findById(id).get();
        // Update the properties of the existing question with the values from the updated question
        existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
        existingQuestion.setOption1(updatedQuestion.getOption1());
        existingQuestion.setOption2(updatedQuestion.getOption2());
        existingQuestion.setOption3(updatedQuestion.getOption3());
        existingQuestion.setOption4(updatedQuestion.getOption4());
        existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
        existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        existingQuestion.setCategory(updatedQuestion.getCategory());

        questiondao.save((existingQuestion));
        return new ResponseEntity<>("Success", HttpStatus.OK );
        //return ResponseEntity.ok("Question updated successfully");
    }
}

