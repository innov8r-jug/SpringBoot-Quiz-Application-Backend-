package com.telusko.quizapp.repository;

import com.telusko.quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //                            <type of the table you're working with i.e Quiz, Primary key >
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
