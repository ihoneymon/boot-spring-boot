package io.honeymon.boot.springboot.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Application 의 준비가 완료되었을 때 발생하는 이벤트 리스너
 * 
 * @author honeymon
 *
 */
@Slf4j
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Application ready: {}", event);
    }

}
