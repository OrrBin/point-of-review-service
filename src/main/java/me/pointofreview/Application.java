package me.pointofreview;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import me.pointofreview.core.objects.Code;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Score;
import me.pointofreview.persistence.MongoDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private MongoDataStore repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {};
    }

}