package com.joseluisestevez.ms.app.exams.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.exams.models.entity.Exam;
import com.joseluisestevez.ms.app.exams.services.ExamService;
import com.joseluisestevez.ms.commons.controllers.CommonController;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Exam exam, @PathVariable Long id) {

        return null;
    }
}
