package tech.wiktor.apis.kpbatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.wiktor.apis.kpbatapi.controllers.message.MessageReplyRequest;
import tech.wiktor.apis.kpbatapi.enums.MessageStatusEnum;
import tech.wiktor.apis.kpbatapi.models.Message;
import tech.wiktor.apis.kpbatapi.repositories.MessageRepository;
import tech.wiktor.apis.kpbatapi.services.MailService;
import tech.wiktor.apis.kpbatapi.services.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired private MessageRepository messageRepository;
    @Autowired private MailService mailService;
    @Autowired private MessageService messageService;

    @PostMapping()
    public ResponseEntity<?> sendMessage(@Valid @RequestBody Message message){
        message.setStatus(MessageStatusEnum.OPENED);
        this.messageRepository.save(message);
        return ResponseEntity.ok("");
    }

    @GetMapping("/admin")
    public List<Message> getAllMessages(@RequestParam(required = false, defaultValue = "0") Integer page) {
        return this.messageService.getPosts(page, Sort.Direction.ASC);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAllMessages(@PathVariable Long id) {
        Message message = this.messageRepository.findById(id).get();

        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message with id " + id + " not found!");

        return ResponseEntity.ok(message);
    }


    @PutMapping("/admin/{id}")
    public ResponseEntity<?> replyMessage(@PathVariable Long id, @Valid @RequestBody MessageReplyRequest messageReplyRequest) {
        Message message = this.messageRepository.findById(id).get();

        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message with id " + id + " not found!");

        if (message.getStatus().equals(MessageStatusEnum.ANSWERED))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message already answered");

        message.setStatus(MessageStatusEnum.ANSWERED);
        this.messageRepository.save(message);
        this.mailService.sendEmail(message.getEmail(), String.format("Reply for message (ID: #%d)", message.getId()), messageReplyRequest.getMessage());
        return ResponseEntity.ok("");
    }
}
