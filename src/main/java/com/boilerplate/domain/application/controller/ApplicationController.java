package com.boilerplate.domain.application.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApplicationController {

    /**
     * 운영 health 체크
     **/
    @GetMapping("/health.html")
    public Boolean healthCheck() {
        return true;
    }

    /**
     * 로봇 크롤링 방지
     **/
    @GetMapping("/robots.txt")
    public void robots(HttpServletResponse response) throws IOException {
        response.getWriter()
                .println("User-agent: *\n" + "Disallow: /");
    }
}
