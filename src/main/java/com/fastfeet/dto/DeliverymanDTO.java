package com.fastfeet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @ToString
public class DeliverymanDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name, email;
}
