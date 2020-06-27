package com.fastfeet.dto.deliverymanDTO;

import com.fastfeet.GlobalVariables;
import com.fastfeet.domain.Deliveryman;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
@Getter @Setter @NoArgsConstructor @ToString
public class DeliverymanListDTO implements Serializable {

    private String id, name, email, avatar;

    private String avatarURL;

    public DeliverymanListDTO(Deliveryman deliveryman) {
        this.id = deliveryman.getId();
        this.name = deliveryman.getName();
        this.email = deliveryman.getEmail();
        this.avatar = deliveryman.getAvatar();
    }

    public String getAvatarURL() {
        if (this.avatar != null) {
            return GlobalVariables.baseURL + "/image/" + avatar;
        } else {
            return null;
        }
    }
}
