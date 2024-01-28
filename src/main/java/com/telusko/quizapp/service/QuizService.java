package com.telusko.quizapp.service;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.entity.Quiz;
import com.telusko.quizapp.entity.Response;
import com.telusko.quizapp.entity.WrappedQuestion;
import com.telusko.quizapp.repository.QuestionDao;
import com.telusko.quizapp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz(); //naya quiz variable banaya which is a new object of Quiz class
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        //now since your quiz is ready, save your quiz
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<WrappedQuestion>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);  //optional because id may or may not exist
        List<Question> questionsFromDB = quiz.get().getQuestions(); //getQuestions is a getter
        List<WrappedQuestion> questionsForUser = new ArrayList<>();
        //till here WrapperQuestion is empty
        //we'll make a for loop
        for(Question q: questionsFromDB)
        {
            WrappedQuestion wq = new WrappedQuestion(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(wq);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionList = quiz.get().getQuestions();
        int right = 0; //basically ek count rakh liya
        int i=0;
        for(Response response: responses)
        {
            if(response.getResponse().equals(questionList.get(i).getRightAnswer()))
            {
                right++ ;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
