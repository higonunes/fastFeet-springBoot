package com.fastfeet.Services;

import com.fastfeet.Services.Exception.ObjectNotFound;
import com.fastfeet.domain.Creator;
import com.fastfeet.dto.UserDTO;

import com.fastfeet.Services.Exception.AuthorizationException;
import com.fastfeet.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreatorService {
    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Creator getCreators(Integer id) {
        var user = SessionService.authenticated();
        if(user == null || !user.getId().equals(id)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        } else {
           return creatorRepository.findById(user.getId()).get();
        }
    }

    public void createCreator(UserDTO userDTO) {
        var user = userDTO.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        creatorRepository.save(user);
    }

    public void deleteCreator(int id) {
        var creator = creatorRepository.findById(id);
        if(creator.isEmpty()) throw new ObjectNotFound("Não encontrado");
        creator.ifPresent(d -> creatorRepository.delete(d));
    }
}
