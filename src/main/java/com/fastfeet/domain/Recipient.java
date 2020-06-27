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
    private String name, rua, complemento, estado, cidade, CEP;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;

    public Recipient(String name, String rua, Integer numero, String complemento, String estado, String cidade, String CEP, Creator creator) {
        this.name = name;
        this.numero = numero;
        this.rua = rua;
        this.complemento = complemento;
        this.estado = estado;
        this.cidade = cidade;
        this.CEP = CEP;
        this.creator = creator;
    }

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
