package com.petclinicdemo.repository;

import com.petclinicdemo.model.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    List<PetType> findAll();
}
