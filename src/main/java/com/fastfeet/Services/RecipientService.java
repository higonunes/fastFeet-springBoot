package com.fastfeet.Services;

import com.fastfeet.dto.RecipientDTO;
import com.fastfeet.Services.Exception.AuthorizationException;
import com.fastfeet.Services.Exception.ObjectNotFound;
import com.fastfeet.domain.Recipient;
import com.fastfeet.repositories.RecipientsRepository;
import com.fastfeet.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    @Autowired
    private RecipientsRepository recipientsRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    public List<Recipient> listRecipientsByUser(Integer userId) {
        var user = SessionService.authenticated();

        if (user == null || !user.getId().equals(userId)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        }

        return recipientsRepository.findAllByCreatorId(userId);
    }

    public void createRecipient(RecipientDTO recipientDTO, Integer userId) {
        var user = SessionService.authenticated();

        if (user == null || !user.getId().equals(userId)) {
            throw new AuthorizationException("Verifique o token ou id do usuário");
        }

        var userRecip = creatorRepository.findById(userId).get();
        var recipient = recipientDTO.toRecipient();
        recipient.setCreator(userRecip);

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
