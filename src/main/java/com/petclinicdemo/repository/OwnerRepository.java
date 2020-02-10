package com.petclinicdemo.repository;

import com.petclinicdemo.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    List<Owner> findAll();

    Owner findByFirstName(String firstName);

    List<Owner> findAllByFirstName(String firstName);

    List<Owner> findAllByLastNameLike(String lastName);
}
