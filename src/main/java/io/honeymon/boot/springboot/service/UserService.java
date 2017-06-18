package io.honeymon.boot.springboot.service;

import io.honeymon.boot.springboot.domain.User;

public interface UserService {

    User findOne(Long id);
}
