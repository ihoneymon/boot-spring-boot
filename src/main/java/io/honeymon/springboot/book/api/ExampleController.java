package io.honeymon.springboot.book.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World";
    }
}
