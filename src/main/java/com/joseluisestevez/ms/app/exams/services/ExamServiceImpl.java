package com.joseluisestevez.ms.app.exams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseluisestevez.ms.app.exams.models.repository.ExamRepository;
import com.joseluisestevez.ms.app.exams.models.repository.SubjectRepository;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.exams.models.entity.Subject;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Exam> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Long> findExamIdWithAnswersAndQuestionIds(Iterable<Long> questionIds) {
        return repository.findExamIdWithAnswersAndQuestionIds(questionIds);
    }

}
