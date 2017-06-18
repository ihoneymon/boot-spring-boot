package io.honeymon.boot.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcTemplateBean {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MyJdbcTemplateBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // 구현사항
}