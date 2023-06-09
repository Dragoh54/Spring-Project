package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Grave;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.MainException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,RoleService roleService) {this.userRepository = userRepository;
        this.roleService = roleService;}



    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        Optional<User> candidate=this.userRepository.findByEmail(email);
        return candidate.isEmpty()?null:candidate.get();
    }
    public User findUserById(Long id) {
        Optional<User> candidate=this.userRepository.findById(id);
        return candidate.isEmpty()?null:candidate.get();
    }

    public UserDto logIn(User user) throws MainException {
        User candidate = findUserByEmail(user.getEmail());
        if (candidate == null) {
            throw new MainException("User with this email never existed!");
        }
        BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(),candidate.getPassword())) {
            return UserMapper.map(candidate);
        }
        throw new MainException("Entered password is incorrect!");
    }

    public UserDto registerUser(User user) throws MainException {
        User candidate = findUserByEmail(user.getEmail());
        if (candidate != null) {
            throw new MainException("User with this email already exists!");
        }
        if(!user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
        {
            throw new MainException("Incorrect email!");
        }
        roleService.setUserRole(user);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        UserDto userDto=UserMapper.map(userRepository.save(user));
        return userDto;
    }

    public UserDto setAdminRole(Long id)throws MainException{
        User candidate = findUserById(id);
        if (candidate == null) {
            throw new MainException("No user was found!");
        }
        roleService.setAdminRole(candidate);
        User updatedUser=userRepository.save(candidate);
        return UserMapper.map(updatedUser);
    }

    public void setUser(Grave grave, Long userId){
        Optional<User> user = userRepository.findById(userId);
        grave.setUser(user.get());
    }
}
