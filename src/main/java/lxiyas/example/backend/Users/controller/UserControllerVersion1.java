package lxiyas.example.backend.Users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lxiyas.example.backend.MainUtils.models.Response;
import lxiyas.example.backend.Users.models.User;
import lxiyas.example.backend.Users.service.UserService;

@RestController
@RequestMapping("/users/api/v1")
@CrossOrigin(origins = "*")
public class UserControllerVersion1 {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> createUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                new Response(true, userService.createUser(user), "user created Successfully"),
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> getUserByEmailAndPassword(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                new Response(true, userService.getUserByEmailAndPassword(user), "fetched user Successfully"),
                HttpStatus.OK);
    }

}