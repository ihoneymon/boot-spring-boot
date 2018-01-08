package io.honeymon.boot.springboot.view;

import org.springframework.web.bind.annotation.GetMapping;

import io.honeymon.boot.springboot.view.annotation.ViewController;

@ViewController
public class ErrorViewController {

	@GetMapping("/occurred-error")
	public String occurError() {
		throw new RuntimeException("불어라 바람아!!");
	}
}
