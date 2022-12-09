package tech.wiktor.apis.kpbatapi.models;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity(name = "messages")
@Getter
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
}
