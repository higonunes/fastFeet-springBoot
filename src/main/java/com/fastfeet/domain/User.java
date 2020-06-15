package com.fastfeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Date createAt;

    @JsonIgnore
    private Date updateAt;


    @OneToMany(mappedBy = "user")
    private List<Recipient> recipients;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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
