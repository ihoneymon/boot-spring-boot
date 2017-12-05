package io.honeymon.boot.springboot.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppArgsAccessor {

    @Autowired
    public AppArgsAccessor(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        log.debug("debug={}", debug);
        
        List<String> files = args.getNonOptionArgs();
        log.debug("files={}", files);
        
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }
}
