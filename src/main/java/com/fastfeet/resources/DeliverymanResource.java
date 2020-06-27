package com.fastfeet.resources;

import com.fastfeet.GlobalVariables;
import com.fastfeet.Services.DeliverymanService;
import com.fastfeet.Services.ImageService;
import com.fastfeet.domain.Deliveryman;
import com.fastfeet.dto.deliverymanDTO.DeliverymanDTO;
import com.fastfeet.dto.deliverymanDTO.DeliverymanListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/deliveryman")
public class DeliverymanResource {

    @Autowired
    private DeliverymanService deliverymanService;

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @GetMapping
    public ResponseEntity<List<DeliverymanListDTO>> listDeliveymen() {
        return ResponseEntity.ok().body(deliverymanService.listAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @PostMapping
    public ResponseEntity<?> createDeliveryman(@RequestParam(required = false) MultipartFile file, @Valid @RequestPart(value = "data") DeliverymanDTO deliverymanDTO ) {
        if (file != null) {
            deliverymanDTO.setAvatar(imageService.saveAvatar(file, null));
        }
        var deliveryman = deliverymanService.createDeliveryman(deliverymanDTO.toDeliveryman());

        if(deliveryman.getAvatar() != null) {
            deliveryman.setAvatar(GlobalVariables.baseURL + "/image/" + deliveryman.getAvatar());
        }
        return ResponseEntity.ok().body(deliveryman);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody DeliverymanDTO deliverymanDTO) {
        return ResponseEntity.ok().body(
                deliverymanService.updateDeliveryman(id, deliverymanDTO.toDeliveryman())
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        deliverymanService.deleteDeliveryman(id);
        return ResponseEntity.ok().build();
    }
}
