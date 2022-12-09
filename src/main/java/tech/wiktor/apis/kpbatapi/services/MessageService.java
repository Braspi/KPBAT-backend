package tech.wiktor.apis.kpbatapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.wiktor.apis.kpbatapi.models.Message;
import tech.wiktor.apis.kpbatapi.repositories.MessageRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private static final int PAGE_SIZE = 10;

    @Autowired
    private MessageRepository messageRepository;


    public List<Message> getPosts(int page, Sort.Direction sort) {
        return this.messageRepository.findAllMessages(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }
}
