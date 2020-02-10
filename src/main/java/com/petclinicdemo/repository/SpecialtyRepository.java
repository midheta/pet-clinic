package com.petclinicdemo.repository;

import com.petclinicdemo.model.Specialty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
    List<Specialty> findAll();
}
