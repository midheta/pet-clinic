package com.petclinicdemo.repository;

import com.petclinicdemo.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> findAll();

}
