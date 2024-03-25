package com.startup.quizapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository quizRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity(quizRepository.findAll(), HttpStatus.ACCEPTED);
        }catch (Exception e){
               e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);

    };

    @PostMapping("/addQuestions")
    public void saveQuestions(@RequestBody List<Question> question){
        quizRepository.saveAll(question);
    }

    @GetMapping("/byCat/{category}")
    public List<Question> getQuestionsbyCategory(@PathVariable String category){
            return quizRepository.findByCategory(category);
    };

    @DeleteMapping("/delete")
    public void deleteByCat(@RequestParam Integer id ){
        quizRepository.deleteById(id);
    }
}
