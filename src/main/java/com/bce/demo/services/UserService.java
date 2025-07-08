package com.bce.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bce.demo.dao.UserRepository;
import com.bce.demo.entities.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        Integer idUser = user.getId();
        User userDb = getUserById(idUser);
        if (userDb != null) {
            userRepository.save(user);
        }
        return userDb;
    }

    public void delete(Integer id) {
        User userDb = getUserById(id);
        if (userDb != null) {
            userRepository.delete(userDb);
        }
    }

}
