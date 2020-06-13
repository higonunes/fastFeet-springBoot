package com.fastfeet;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fastfeet.domain.User;
import com.fastfeet.repositories.UserRepository;
import com.fastfeet.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class FastFeetApplication implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(FastFeetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAll(Arrays.asList(
                new User("João Higo", bCryptPasswordEncoder.encode("12345"), "higo.sousaa@gmail.com"),
                new User("Iones Maria", bCryptPasswordEncoder.encode("12345"), "iones.sousaa@gmail.com")
        ));
    }
}
