package com.example.structural_pattern._7_bridge.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jExample {

    private static Logger logger = LoggerFactory.getLogger(Slf4jExample.class);

    public static void main(String[] args) {
        logger.info("hello logger");
    }
}
