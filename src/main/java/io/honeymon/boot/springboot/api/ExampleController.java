package io.honeymon.boot.springboot.api;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource(value = { "" })
@RestController
public class ExampleController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World";
    }
}
