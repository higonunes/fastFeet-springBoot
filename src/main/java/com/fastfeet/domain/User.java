package com.fastfeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

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
