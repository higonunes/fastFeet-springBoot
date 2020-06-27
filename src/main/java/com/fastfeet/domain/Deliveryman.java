package com.fastfeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class Deliveryman implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name, avatar, email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;

    @JsonIgnore
    private Date createdAt, updatedAt;

    public Deliveryman(String name, String email, String avatar) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

}
