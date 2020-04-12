package com.joseluisestevez.ms.app.exams.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseluisestevez.ms.app.exams.models.repository.ExamRepository;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {

    @Transactional(readOnly = true)
    @Override
    public List<Exam> findByName(String name) {
        return repository.findByName(name);
    }

}
