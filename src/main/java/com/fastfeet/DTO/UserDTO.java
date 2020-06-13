package com.fastfeet.DTO;

import com.fastfeet.domain.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @NotEmpty(message = "O email é obrigatório")
    private String name;

    @NotEmpty(message = "O email é obrigatório")
    @Column(unique = true)
    @Email(message = "Insira um email válido")
    private String email;

    @NotEmpty(message = "O email é obrigatório")
    private String password;

    public User toUser() {
        return new User(this.name, bCryptPasswordEncoder.encode(this.password), this.email);
    }

}
