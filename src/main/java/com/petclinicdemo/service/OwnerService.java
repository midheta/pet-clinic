package com.petclinicdemo.service;

import com.petclinicdemo.model.Owner;
import com.petclinicdemo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService implements CrudService<Owner> {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findOneById(Long id) {
        Optional<Owner> result = ownerRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    public List<Owner> findByFirstName(String firstName) {
        return ownerRepository.findAllByFirstName(firstName);
    }

    public List<Owner> findAllByLastName(String lastName) {
        return ownerRepository.findAllByLastNameLike("%" + lastName + "%");
    }

    public void activateNavbar(Model model) {
        model.addAttribute("classActiveHome", "inactive");
        model.addAttribute("classActiveOwner", "active");
        model.addAttribute("classActiveVets", "inactive");
    }

}
