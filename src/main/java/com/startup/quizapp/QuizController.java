package com.startup.quizapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        List<Question> quizQuestions = questionRepository.findQuestionsbyId(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(quizQuestions);
        quizRepository.save(quiz);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @GetMapping("/getQuiz")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@RequestParam int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.equals(Optional.empty()) && quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> quizQuestions = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper questionWrappers = new QuestionWrapper(q.getId(), q.getCategory(), q.getQuestions(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            quizQuestions.add(questionWrappers);

        }
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);

    }

    @PostMapping("/submit")
    public int getQuiz(@RequestParam int id, @RequestBody List<Response> responses) {

        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();
        int i = 0;
        int right = 0;
        for (Response r : responses) {
            if (r.response == questions.get(i).getRightAnswer()) {
                right++;
            }
            i++;
        }
        return right;

    }
}
