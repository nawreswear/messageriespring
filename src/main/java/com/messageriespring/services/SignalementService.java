package com.messageriespring.services;

import com.messageriespring.model.Signalement;
import com.messageriespring.model.User;
import com.messageriespring.repository.SignalementRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SignalementService {

    @Autowired
    private SignalementRepository signalementRepository;
    @Transactional
    public Signalement saveSignalement(String message, Date date, User user, List<User> users, String type) {
        Signalement signalement = new Signalement();
        signalement.setMessage(message);
        signalement.setDate(date);
        signalement.setUser(user);
        signalement.setUsers(users);
        signalement.setType(type);

        return signalementRepository.save(signalement);
    }
    @Transactional
    public List<Signalement> getAllSignalements() {
        List<Signalement> signalements = signalementRepository.findAll();
        for (Signalement signalement : signalements) {
            if (signalement.getUser() != null) {
                Hibernate.initialize(signalement.getUser().getMessages());
            }
        }
        return signalements;
    }
}
