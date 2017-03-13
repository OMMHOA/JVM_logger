package com.oktafone;

import com.oktafone.amqp.Receiver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Main implements CommandLineRunner {

    @Bean
    Receiver receiver(){
        return new Receiver();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App is running");
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
