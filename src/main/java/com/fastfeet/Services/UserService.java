package com.fastfeet.Services;

import com.fastfeet.DTO.UserDTO;
import com.fastfeet.domain.User;
import com.fastfeet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserDTO userDTO) {
        var user = userDTO.toUser();
        userRepository.save(user);
    }
}
