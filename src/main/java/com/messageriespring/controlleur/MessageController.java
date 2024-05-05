package com.messageriespring.controlleur;


import com.messageriespring.exception.ResourceNotFoundException;
import com.messageriespring.model.Message;
import com.messageriespring.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "localhost:4200")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
@GetMapping("/message")
public List<Message> getMessages() {
    return (List<Message>) messageRepository.findAll();
}
@PostMapping("/messages")
public Message createMessage(@Valid @RequestBody Message message) {
    return messageRepository.save(message);
}

@GetMapping("/messages/{id}")
public Message getMessageById(@PathVariable(value = "id") Long messageId) {
    return messageRepository.findById(messageId)
            .orElseThrow(() -> new ResourceNotFoundException("Message", "id", messageId));
}
@DeleteMapping("/messages/{id}")
public ResponseEntity<?> deleteMessage(@PathVariable(value = "id") Long messageId) {
    Message message = messageRepository.findById(messageId)
            .orElseThrow(() -> new ResourceNotFoundException("Message", "id", messageId));

    messageRepository.delete(message);

    return ResponseEntity.ok().build();
}
}