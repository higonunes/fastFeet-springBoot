package com.fastfeet.resources;

import javax.validation.Valid;

import com.fastfeet.dto.UserDTO;
import com.fastfeet.Services.CreatorService;
import com.fastfeet.domain.Creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class CreatorResource {

    @Autowired
    private CreatorService creatorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Creator> listByUser(@PathVariable Integer id) {
        return ResponseEntity.ok().body(creatorService.getCreators(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {
        creatorService.createCreator(userDTO);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        creatorService.deleteCreator(id);
        return ResponseEntity.ok().build();
    }
}
