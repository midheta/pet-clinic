package com.petclinicdemo.service;

import com.petclinicdemo.model.Vet;
import com.petclinicdemo.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService implements CrudService<Vet>{

    private VetRepository vetRepository;

    @Autowired
    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }


    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    @Override
    public Vet findOneById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {

        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

}
