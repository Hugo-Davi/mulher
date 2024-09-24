package com.tatu.mulher.controllers;

import com.google.gson.Gson;
import com.tatu.mulher.models.User;
import com.tatu.mulher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private long idArmazenado;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getUsers(){
        String json;
        json = new Gson().toJson(this.userService.getAllUsers());
        return ResponseEntity.ok(json);
    }

    @RequestMapping(value = "/valor", method = RequestMethod.GET)
    public String getIdArmazenado(){
        return "Id Armazenado no server é: " + this.idArmazenado;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUserById (@PathVariable("id") long id){
        System.out.println(id);
        this.idArmazenado = id;
        return "O id desse usuário é: " + id;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser (@RequestBody User user){
        this.userService.createUser(user);
        return ResponseEntity.ok("{status:funcionou}");
    }
}