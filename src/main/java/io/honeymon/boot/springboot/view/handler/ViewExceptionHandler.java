package io.honeymon.boot.springboot.view.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import io.honeymon.boot.springboot.view.annotation.ViewController;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ViewController} 에 대한 예외처리
 * @author honeymon
 *
 */
@Slf4j
@ControllerAdvice(annotations= {ViewController.class})
public class ViewExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Occurred Exception: {}", e.getMessage());
        ModelAndView mav = new ModelAndView("view-error");
        mav.addObject("e", e);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
