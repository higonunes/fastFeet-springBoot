package com.fastfeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastfeet.enums.Perfil;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Creator implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Date createAt;

    @JsonIgnore
    private Date updateAt;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private List<Deliveryman> deliverymen;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private List<Recipient> recipients;


    public Creator(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        addPerfil(Perfil.CREATOR);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getId());
    }

    @PrePersist
    void createdAt() {
        this.createAt = this.updateAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updateAt = new Date();
    }

}
