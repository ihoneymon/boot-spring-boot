package io.honeymon.boot.springboot.view;

import org.springframework.web.bind.annotation.GetMapping;

import io.honeymon.boot.springboot.view.annotation.ViewController;

@ViewController
public class GreetingController {

    @GetMapping("/greeting")
    public String root() {
        return "Hello, world!";
    }
}
