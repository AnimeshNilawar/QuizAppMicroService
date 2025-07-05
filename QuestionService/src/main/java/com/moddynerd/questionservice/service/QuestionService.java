package com.moddynerd.questionservice.service;

import com.moddynerd.questionservice.dao.QuestionDao;
import com.moddynerd.questionservice.model.Question;
import com.moddynerd.questionservice.model.QuestionWrapper;
import com.moddynerd.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(questionDao.findByCategory(keyword), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {

        if (question == null) {
            return new ResponseEntity<>("fail: Question must not be null.", HttpStatus.BAD_REQUEST);
        }

        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Question question) {

        if (question == null || question.getId() == null) {
            return new ResponseEntity<>("fail: Question or ID must not be null.", HttpStatus.BAD_REQUEST);
        }

        Optional<Question> existingQuestion = questionDao.findById(question.getId());

        if (existingQuestion.isEmpty()) {
            return new ResponseEntity<>("fail: Question with ID " + question.getId() + " does not exist.", HttpStatus.NOT_FOUND);
        } else {
            question.setId(existingQuestion.get().getId());

            try {
                questionDao.save(question);
                return new ResponseEntity<>("success", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("fail: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    public ResponseEntity<String> deleteQuestion(Integer id) {

        if (id == null){
            return new ResponseEntity<>("fail: ID must not be null.", HttpStatus.BAD_REQUEST);
        }

        Optional<Question> existingQuestion = questionDao.findById(id);

        if (existingQuestion.isEmpty()) {
            return new ResponseEntity<>("fail: Question with ID " + id + " does not exist.", HttpStatus.NOT_FOUND);
        } else {
            try {
                questionDao.deleteById(id);
                return new ResponseEntity<>("success", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("fail: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        if (categoryName == null || categoryName.isEmpty() || numQuestions == null || numQuestions <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        if (questions.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for (Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;

        for(Response response : responses) {
            Question question = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(question.getCorrectAns()))
                score++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
