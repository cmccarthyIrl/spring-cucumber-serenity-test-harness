package com.cmccarthy.common.utils;

import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HookUtils {

    private final Logger log = LoggerFactory.getLogger(HookUtils.class);

    public void endOfTest(Scenario scenario) {
        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
