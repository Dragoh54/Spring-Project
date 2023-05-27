package com.example.demo.service;

import com.example.demo.entity.Grave;
import com.example.demo.exception.MainException;
import com.example.demo.repository.GraveRepository;

import java.util.List;
import java.util.Optional;

public class GraveService {
    private GraveRepository graveRepository;

    public GraveService(GraveRepository graveRepository) {
        this.graveRepository = graveRepository;
    }

    public Grave findById(Long id) throws MainException {
        Optional<Grave> candidate = graveRepository.findById(id);
        if(!candidate.isPresent()){
            throw new MainException("Grave with this id does not exist!");
        }
        return candidate.get();
    }

    public Grave findByPersonName(String name) throws MainException{
        Optional<Grave> candidate = graveRepository.findByPersonName(name);
        if(!candidate.isPresent()){
            throw new MainException("Grave with this person does not exist!");
        }
        return candidate.get();
    }

    public void addGrave(Grave newGrave) throws MainException {
        Optional<Grave> candidate = graveRepository.findByPersonName(newGrave.getName());
        if(!candidate.isPresent()){
            throw new MainException("Grave with this person already exist!");
        }
        graveRepository.save(newGrave);
    }

    public List<Grave> getGraves(){
        return graveRepository.findAll();
    }

    public void saveGrave(Grave movie) {
        graveRepository.save(movie);
    }

    public void deleteGrave(Long id) {
        graveRepository.deleteById(id);
    }
}
