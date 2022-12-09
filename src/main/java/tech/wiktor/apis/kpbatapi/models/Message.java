package tech.wiktor.apis.kpbatapi.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import tech.wiktor.apis.kpbatapi.enums.MessageStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name field can't be null")
    private String name;

    @Email(message = "email format is invalid")
    private String email;

    @NotEmpty(message = "phone field can't be null")
    private String phone;

    @Type(type = "text")
    @NotEmpty(message = "message field can't be null")
    private String message;

    @Column(length = 32, columnDefinition = "varchar(32) default 'OPENED'")
    @Enumerated(EnumType.STRING)
    private MessageStatusEnum status;
}
