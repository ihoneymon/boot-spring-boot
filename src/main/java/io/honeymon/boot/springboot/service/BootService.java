package io.honeymon.boot.springboot.service;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.honeymon.boot.springboot.config.ExampleProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BootService {

    private ExampleProperties properties;
    
    @Autowired
    public BootService(ExampleProperties properties) {
        this.properties = properties;
    }
    
    @PostConstruct
    public void init() {
        log.debug("Injected properties: {}", this.properties);
    }
}
