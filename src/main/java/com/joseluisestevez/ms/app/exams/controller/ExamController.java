package com.joseluisestevez.ms.app.exams.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.exams.services.ExamService;
import com.joseluisestevez.ms.commons.controllers.CommonController;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.exams.models.entity.Question;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @GetMapping("/answered-by-questions")
    public ResponseEntity<?> getAnsweredByQuestions(@RequestParam List<Long> questionIds) {
        return ResponseEntity.ok().body(service.findExamIdWithAnswersAndQuestionIds(questionIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validate(result);
        }
        Optional<Exam> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Exam currentExam = o.get();
        currentExam.setName(exam.getName());
        currentExam.setParentSubject(exam.getParentSubject());
        currentExam.setChildrenSubject(exam.getChildrenSubject());

        // Option A: Using CopyOnWriteArrayList
        // CopyOnWriteArrayList<Question> questions = new CopyOnWriteArrayList<>(currentExam.getQuestions());
        // questions.stream().filter(cq -> !exam.getQuestions().contains(cq)).forEach(currentExam::removeQuestion);

        // Option B:
        List<Question> removed = currentExam.getQuestions().stream().filter(cq -> !exam.getQuestions().contains(cq)).collect(Collectors.toList());
        removed.forEach(currentExam::removeQuestion);

        currentExam.setQuestions(exam.getQuestions());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(currentExam));
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<?> filter(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> listSubjects() {
        return ResponseEntity.ok(service.findAllSubjects());
    }
}
