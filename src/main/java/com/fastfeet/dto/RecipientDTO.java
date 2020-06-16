package com.fastfeet.dto;

import com.fastfeet.domain.Recipient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipientDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer numero;

    @NotEmpty
    private String name, rua, estado, cidade, CEP;

    private String complemento;

    public Recipient toRecipient() {
        return new Recipient(this.name, this.rua, this.numero, this.complemento, this.estado, this.cidade, this.CEP, null);
    }
}
