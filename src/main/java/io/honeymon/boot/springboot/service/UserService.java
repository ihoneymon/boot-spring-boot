package io.honeymon.boot.springboot.service;

import java.util.Optional;

import io.honeymon.boot.springboot.domain.User;

public interface UserService {

    Optional<User> findById(Long id);
}
