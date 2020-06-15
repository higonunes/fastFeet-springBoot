package com.fastfeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class Recipient implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private String rua, complemento, estado, cidade, CEP;

    public Recipient(Integer numero, String rua, String complemento, String estado, String cidade, String CEP, User user) {
        this.numero = numero;
        this.rua = rua;
        this.complemento = complemento;
        this.estado = estado;
        this.cidade = cidade;
        this.CEP = CEP;
        this.user = user;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    private Date createAt;

    @JsonIgnore
    private Date updateAt;

    @PrePersist
    void createdAt() {
        this.createAt = this.updateAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updateAt = new Date();
    }
}