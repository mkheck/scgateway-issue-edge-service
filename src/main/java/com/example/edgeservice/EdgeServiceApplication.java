package com.example.edgeservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }
}

@RefreshScope
@RestController
class EdgeServiceController {
    @Value("${quote:'Hi Mom! Call your mother.'}")
    private String defaultQuote;

    @GetMapping("/defaultquote")
    public Mono<Quote> getDefaultQuote() {
        return Mono.just(new Quote("000-default-999", "All of us here in NYC!", defaultQuote));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Quote {
    private String id, source, text;
}