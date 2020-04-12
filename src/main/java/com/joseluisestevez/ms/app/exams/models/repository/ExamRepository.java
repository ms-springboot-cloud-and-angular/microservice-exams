package com.joseluisestevez.ms.app.exams.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.joseluisestevez.ms.app.exams.models.entity.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {

}
