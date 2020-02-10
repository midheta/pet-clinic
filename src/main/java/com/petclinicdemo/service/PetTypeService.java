package com.petclinicdemo.service;

import com.petclinicdemo.model.PetType;
import com.petclinicdemo.repository.PetTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTypeService implements CrudService<PetType> {

    private PetTypeRepository petTypeRepository;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public List<PetType> findAll() {
        return petTypeRepository.findAll();
    }

    @Override
    public PetType findOneById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
