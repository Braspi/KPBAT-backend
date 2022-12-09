package tech.wiktor.apis.kpbatapi.controllers.message;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MessageReplyRequest {
    @NotEmpty(message = "message can't be empty!")
    private String message;
}
