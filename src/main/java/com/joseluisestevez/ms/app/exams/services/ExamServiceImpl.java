package com.joseluisestevez.ms.app.exams.services;

import org.springframework.stereotype.Service;

import com.joseluisestevez.ms.app.exams.models.repository.ExamRepository;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {

}
