package io.honeymon.boot.springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.honeymon.boot.springboot.domain.User;
import io.honeymon.boot.springboot.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService service;
    
    @GetMapping("/{id}")
    public User findOne(@PathVariable long id) {
        return service.findOne(id);
    }
}
