package com.fastfeet.resources;

import com.fastfeet.Services.DeliverymanService;
import com.fastfeet.domain.Deliveryman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/deliveryman")
public class DeliverymanResource {

    @Autowired
    private DeliverymanService deliverymanService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Deliveryman>> listDeliveymen() {
        return ResponseEntity.ok().body(deliverymanService.listAll());
    }

}
