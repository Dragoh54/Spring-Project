package com.example.demo.service;

import com.example.demo.entity.Grave;
import com.example.demo.entity.User;
import com.example.demo.exception.MainException;
import com.example.demo.repository.GraveRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraveService {
    private GraveRepository graveRepository;
    private UserService userService;

    public GraveService(GraveRepository graveRepository, UserService userService) {
        this.graveRepository = graveRepository;
        this.userService = userService;
    }

    public Grave findById(Long id) throws MainException {
        Optional<Grave> candidate = graveRepository.findById(id);
        if(!candidate.isPresent()){
            throw new MainException("Grave with this id does not exist!");
        }
        return candidate.get();
    }

    public Grave findByPersonName(String name) throws MainException{
        Optional<Grave> candidate = graveRepository.findByName(name);
        if(!candidate.isPresent()){
            throw new MainException("Grave with this person does not exist!");
        }
        return candidate.get();
    }

    public void addGrave(Grave newGrave) throws MainException {
        Optional<Grave> candidate = graveRepository.findByName(newGrave.getName());
        if(!candidate.isPresent()){
            throw new MainException("Grave with this person already exist!");
        }
        graveRepository.save(newGrave);
    }

    public List<Grave> getGraves(){
        return graveRepository.findAll();
    }

    public void saveGrave(Grave grave, Long userId) {
        userService.setUser(grave, userId);
        graveRepository.save(grave);
    }

    public void deleteGrave(Long id) {
        graveRepository.deleteById(id);
    }

    public List<Grave> getGravesByUser(Long userId){
        System.out.println(graveRepository.findByUserId(userId));
        return graveRepository.findByUserId(userId);
    }
}
