package com.fastfeet.Services;

import com.fastfeet.Services.Exception.AuthorizationException;
import com.fastfeet.Services.Exception.ObjectNotFound;
import com.fastfeet.Services.util.GenerateID;
import com.fastfeet.domain.Deliveryman;
import com.fastfeet.dto.deliverymanDTO.DeliverymanListDTO;
import com.fastfeet.repositories.CreatorRepository;
import com.fastfeet.repositories.DeliverymanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliverymanService {

    @Autowired
    private DeliverymanRepository deliverymanRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private ImageService imageService;

    public List<DeliverymanListDTO> listAll() {
        var all = deliverymanRepository.findAll();
        return all.stream().map(DeliverymanListDTO::new).collect(Collectors.toList());
    }

    public Deliveryman createDeliveryman(Deliveryman deliveryman) {
        var userSS = SessionService.authenticated();
        if(userSS != null) {
            var creator = creatorRepository.findById(userSS.getId()).get();
            deliveryman.setId(GenerateID.generateValue());
            deliveryman.setCreator(creator);
            return deliverymanRepository.save(deliveryman);
        } else {
            throw new AuthorizationException("Não autorizado");
        }
    }

    public Deliveryman updateDeliveryman(String id, Deliveryman deliveryman) {
        var userSS = SessionService.authenticated();
        if(userSS != null) {
            var result = deliverymanRepository.findById(id);
            if (result.isPresent()) {
                result.get().setEmail(deliveryman.getEmail());
                result.get().setName(deliveryman.getName());
                deliverymanRepository.save(result.get());
                return result.get();
            } else {
                throw new ObjectNotFound("ID inválida");
            }
        }
        return null;
    }

    public void deleteDeliveryman(String id) {
        var deliveryman = deliverymanRepository.findById(id);
        if(deliveryman.isEmpty()) throw new ObjectNotFound("Não encontrado");
        deliverymanRepository.delete(deliveryman.get());
    }
}
