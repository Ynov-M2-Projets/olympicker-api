package com.ynov.olympicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@Controller
public class OlympickerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OlympickerApplication.class, args);
    }


    @RequestMapping("/")
    @ResponseBody
    RedirectView home() {
        return new RedirectView("/api");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OlympickerApplication.class);
    }
}
