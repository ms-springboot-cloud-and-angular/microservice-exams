package com.joseluisestevez.ms.app.exams.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.joseluisestevez.ms.commons.exams.models.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

}
