package com.petclinicdemo.repository;

import com.petclinicdemo.model.Pet;
import com.petclinicdemo.model.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VetRepository extends CrudRepository<Vet, Long> {

    List<Vet> findAll();
}
