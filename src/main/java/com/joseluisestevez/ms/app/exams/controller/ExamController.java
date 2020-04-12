package com.joseluisestevez.ms.app.exams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.exams.models.entity.Exam;
import com.joseluisestevez.ms.app.exams.models.entity.Question;
import com.joseluisestevez.ms.app.exams.services.ExamService;
import com.joseluisestevez.ms.commons.controllers.CommonController;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Exam exam, @PathVariable Long id) {

        Optional<Exam> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Exam currentExam = o.get();
        currentExam.setName(exam.getName());

        List<Question> deletedQuestions = new ArrayList<>();

        currentExam.getQuestions().forEach(cq -> {
            if (!exam.getQuestions().contains(cq)) {
                deletedQuestions.add(cq);
            }
        });

        deletedQuestions.forEach(dq -> {
            currentExam.removeQuestion(dq);
        });

        currentExam.setQuestions(exam.getQuestions());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(currentExam));
    }
}
