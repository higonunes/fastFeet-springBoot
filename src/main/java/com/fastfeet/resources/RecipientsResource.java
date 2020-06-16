package com.fastfeet.resources;

import com.fastfeet.dto.RecipientDTO;
import com.fastfeet.Services.RecipientService;
import com.fastfeet.domain.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/recipient")
public class RecipientsResource {

    @Autowired
    private RecipientService recipientService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> createRecipient(@PathVariable Integer userId, @Valid @RequestBody RecipientDTO recipientDTO) {
        recipientService.createRecipient(recipientDTO, userId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Recipient>> list(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(recipientService.listRecipientsByUser(userId));
    }

    @GetMapping(value = "/{userId}/{id}")
    public ResponseEntity<Recipient> getRecipient(@PathVariable Integer userId, @PathVariable Long id) {
        return ResponseEntity.ok().body(recipientService.getRecipientById(userId, id));
    }
}
