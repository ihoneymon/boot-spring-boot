package io.honeymon.boot.springboot.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Application 이 구동되는 순간 발생하는 리스너
 * 
 * @author honeymon
 *
 */
@Slf4j
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
         log.info("Application Start: {}", event);
    }

}
