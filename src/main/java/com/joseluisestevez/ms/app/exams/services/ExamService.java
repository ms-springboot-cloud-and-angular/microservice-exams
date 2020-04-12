package com.joseluisestevez.ms.app.exams.services;

import java.util.List;

import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.exams.models.entity.Subject;
import com.joseluisestevez.ms.commons.services.CommonService;

public interface ExamService extends CommonService<Exam> {
    List<Exam> findByName(String name);

    Iterable<Subject> findAllSubjects();
}
