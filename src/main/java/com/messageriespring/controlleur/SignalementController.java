package com.messageriespring.controlleur;

import com.messageriespring.model.Signalement;
import com.messageriespring.model.User;
import com.messageriespring.repository.SignalementRepository;
import com.messageriespring.services.SignalementService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "localhost:4200")
public class SignalementController {
    @Autowired
    private SignalementRepository signalementRepository;
    @Autowired
    private SignalementService signalementservice;
    @GetMapping("/{id}")
    public Signalement getSignalementById(long id) {
        Optional<Signalement> optionalSignalement = signalementRepository.findById(id);
        return optionalSignalement.orElse(null);
    }
    @PostMapping(value="/savee")
    public Signalement savee(@RequestBody Signalement signalement) {
        String message = signalement.getMessage();
        Date date = signalement.getDate();
        User user = signalement.getUser();
        List<User> users = signalement.getUsers();
        String type = signalement.getType();
        return signalementservice.saveSignalement(message, date, user, users, type);
    }

    @GetMapping("/signalements")
    public List<Signalement> getAllSignalement() {
        List<Signalement> signalements = signalementservice.getAllSignalements();
        for (Signalement signalement : signalements) {
            if (signalement.getUser() != null) {
                Hibernate.initialize(signalement.getUser().getMessages());
            }
        }
        return signalements;
    }



    @PostMapping(value="/save")
    public Signalement save(Signalement signalement) {
        return signalementRepository.save(signalement);
    }

    @PutMapping("/{id}")
    public Signalement updateSignalement(long id, Signalement signalement) {
        Optional<Signalement> existingSignalementOptional = signalementRepository.findById(id);
        if (existingSignalementOptional.isPresent()) {
            Signalement existingSignalement = existingSignalementOptional.get();
            existingSignalement.setMessage(signalement.getMessage());
            existingSignalement.setDate(signalement.getDate());
            existingSignalement.setUser(signalement.getUser());
            existingSignalement.setType(signalement.getType());
            return signalementRepository.save(existingSignalement);
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteSignalement(long id) {
        signalementRepository.deleteById(id);
    }







}
