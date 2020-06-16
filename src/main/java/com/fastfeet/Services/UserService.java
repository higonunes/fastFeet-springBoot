package com.fastfeet.Services;

import com.fastfeet.dto.UserDTO;

import com.fastfeet.Services.Exception.AuthorizationException;
import com.fastfeet.domain.User;
import com.fastfeet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUser(Integer id) {
        var user = SessionService.authenticated();
        if(user == null || !user.getId().equals(id)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        } else {
           return userRepository.findById(user.getId()).get();
        }
    }

    public void createUser(UserDTO userDTO) {
        var user = userDTO.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
