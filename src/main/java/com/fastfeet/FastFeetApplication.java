package com.fastfeet;

import com.fastfeet.domain.Recipient;
import com.fastfeet.domain.User;
import com.fastfeet.repositories.RecipientsRepository;
import com.fastfeet.repositories.UserRepository;
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

    @Autowired
    private RecipientsRepository recipientsRepository;

    public static void main(String[] args) {
        SpringApplication.run(FastFeetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user1 = new User("João Higo", bCryptPasswordEncoder.encode("12345"), "higo.sousaa@gmail.com");
        userRepository.saveAll(Arrays.asList(
                user1,
                new User("Iones Maria", bCryptPasswordEncoder.encode("12345"), "iones.sousaa@gmail.com")
        ));

        recipientsRepository.saveAll(Arrays.asList(
                new Recipient("João Higo Sousa Nunes", "Rua coronel euripedes bezerra", 2, "Aririzal Residence", "MA", "São Luís", "65066-260", user1)
        ));
    }
}
