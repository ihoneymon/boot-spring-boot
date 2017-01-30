package io.honeymon.springboot.book.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWeb {

	@GetMapping("/")
	public String hello() {
		return "Hello, everyone!";
	} 
}
