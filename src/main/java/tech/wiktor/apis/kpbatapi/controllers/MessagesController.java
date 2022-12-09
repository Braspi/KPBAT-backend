package tech.wiktor.apis.kpbatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wiktor.apis.kpbatapi.models.Message;
import tech.wiktor.apis.kpbatapi.repositories.MessageRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    MessageRepository messageRepository;

    @PostMapping()
    public ResponseEntity<?> sendMessage(@Valid @RequestBody Message message){
        this.messageRepository.save(message);
        return ResponseEntity.ok("");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllMessages(){
        List<Message> messages = this.messageRepository.findAll();
        return ResponseEntity.ok(messages);
    }
}
