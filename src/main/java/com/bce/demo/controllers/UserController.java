package com.bce.demo.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bce.demo.dto.UserDto;
import com.bce.demo.entities.User;
import com.bce.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/greet")
    public String sayHello() {
        return "Hola Mundo";
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable("id") Integer id){
        ModelMapper modelMapper = new ModelMapper();
        User userDb = userService.getUserById(id);
        return modelMapper.map(userDb, UserDto.class);
    }


    @GetMapping("")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/add-users")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        return new ResponseEntity<List<User>>(userService.createUsers(users), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.delete(userId);
    }

}
