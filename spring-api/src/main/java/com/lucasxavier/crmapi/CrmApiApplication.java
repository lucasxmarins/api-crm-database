package com.lucasxavier.crmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApiApplication.class, args);

        /*
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        String result = bCryptPasswordEncoder.encode("senha123");
        System.out.println("Minha senha: "+ result);
        */
    }

}
