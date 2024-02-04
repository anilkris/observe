package com.myc.observe;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyController {

    @Value("${spring.application.name}")
    private String applicationName;

    private RestTemplate restTemplate;


    Random random = new Random();

    public MyController( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    @GetMapping("/path1")
    public ResponseEntity<String> path1() {

        log.info("Incoming request at {} for request /path1 ", applicationName);

        String path1 = "Response from Path1 ";

        
        String response = restTemplate.getForObject("http://localhost:9321/path2", String.class);

        return ResponseEntity.ok(path1 + response);
    }

    @GetMapping("/path2")

    public ResponseEntity<String> path2() {

        log.info("Incoming request at {} for request /path2 ", applicationName);

        return ResponseEntity.ok("response from /path2 ");

    }

    public void doNothingButSleepForSomeTime() {
        try {
            int sleepTime = random.nextInt(1, 2);
            log.info("sleeping for " + sleepTime + " seconds");
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
