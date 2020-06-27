package com.fastfeet.dto;

import com.fastfeet.domain.Creator;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O nome é obrigatório")
    private String name;

    @NotEmpty(message = "O email é obrigatório")
    @Email(message = "Insira um email válido")
    private String email;

    @NotEmpty(message = "A senha é obrigatória")
    private String password;

    public Creator toUser() {
        return new Creator(this.name, this.password, this.email);
    }
}