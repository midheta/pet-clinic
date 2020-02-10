package com.petclinicdemo.repository;

import com.petclinicdemo.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findAll();
}
