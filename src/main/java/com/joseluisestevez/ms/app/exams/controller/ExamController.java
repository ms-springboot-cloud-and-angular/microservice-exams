package com.joseluisestevez.ms.app.exams.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.exams.services.ExamService;
import com.joseluisestevez.ms.commons.controllers.CommonController;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Exam exam, @PathVariable Long id) {

        Optional<Exam> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Exam currentExam = o.get();
        currentExam.setName(exam.getName());

        currentExam.getQuestions().stream().filter(cq -> !exam.getQuestions().contains(cq)).forEach(currentExam::removeQuestion);

        currentExam.setQuestions(exam.getQuestions());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(currentExam));
    }
}
