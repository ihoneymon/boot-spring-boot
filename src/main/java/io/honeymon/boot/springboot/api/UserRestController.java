package io.honeymon.boot.springboot.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.honeymon.boot.springboot.domain.User;
import io.honeymon.boot.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService service;
    
    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        log.info("Get User: {}", id);
       Optional<User> optUser = service.findById(id);
       
       return optUser.isPresent() ? optUser.get() : new User();
    }
}
