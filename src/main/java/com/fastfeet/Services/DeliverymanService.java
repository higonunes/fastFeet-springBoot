package com.fastfeet.Services;

import com.fastfeet.Services.util.GenerateID;
import com.fastfeet.domain.Deliveryman;
import com.fastfeet.repositories.DeliverymanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverymanService {

    @Autowired
    private DeliverymanRepository deliverymanRepository;

    public List<Deliveryman> listAll() {
        return deliverymanRepository.findAll();
    }

    public void createDeliveryman(Deliveryman deliveryman) {
        deliveryman.setId(GenerateID.generateValue());
        deliverymanRepository.save(deliveryman);
    }
}
