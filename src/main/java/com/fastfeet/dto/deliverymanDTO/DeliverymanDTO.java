package com.fastfeet.dto.deliverymanDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastfeet.domain.Deliveryman;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Component
@Getter @Setter @NoArgsConstructor @ToString
public class DeliverymanDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name, email;

    private String avatar;

    public Deliveryman toDeliveryman() {
        return new Deliveryman( this.name, this.email, this.avatar);
    }
}
