package com.fastfeet.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SessionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

}
