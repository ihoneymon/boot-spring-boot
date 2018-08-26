package io.honeymon.boot.springboot.view;

import org.springframework.web.bind.annotation.GetMapping;

import io.honeymon.boot.springboot.view.annotation.ViewController;

@ViewController
public class RootController {

    @GetMapping("/root")
    public String root() {
        return "root";
    }
}
