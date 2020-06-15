package com.fastfeet.Services;

import com.fastfeet.DTO.RecipientDTO;
import com.fastfeet.Services.Exception.AuthorizationException;
import com.fastfeet.Services.Exception.ObjectNotFound;
import com.fastfeet.domain.Recipient;
import com.fastfeet.repositories.RecipientsRepository;
import com.fastfeet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    @Autowired
    private RecipientsRepository recipientsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Recipient> listRecipientsByUser(Integer userId) {
        var user = SessionService.authenticated();

        if (user == null || !user.getId().equals(userId)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        }

        return recipientsRepository.findAllByUserId(userId);
    }

    public void createRecipient(RecipientDTO recipientDTO, Integer userId) {
        var user = SessionService.authenticated();

        if (user == null || !user.getId().equals(userId)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        }

        var userRecip = userRepository.findById(userId).get();
        var recipient = recipientDTO.toRecipient();
        recipient.setUser(userRecip);

        recipientsRepository.save(recipient);
    }

    public Recipient getRecipientById(Integer userId, Long id) {
        var user = SessionService.authenticated();

        if (user == null || !user.getId().equals(userId)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        }

        var response = recipientsRepository.findById(id);

        if (response.isPresent()) {
            return response.get();
        } else {
            throw new ObjectNotFound("Recipient não encontrado");
        }

    }
}
