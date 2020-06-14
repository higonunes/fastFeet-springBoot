package com.fastfeet.DTO;

import com.fastfeet.domain.User;
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

    private String email;
    private String password;

}
