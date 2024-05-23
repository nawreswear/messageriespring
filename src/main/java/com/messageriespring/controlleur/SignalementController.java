package com.messageriespring.controlleur;
import com.messageriespring.model.Article;
import com.messageriespring.model.Signalement;
import com.messageriespring.model.User;
import com.messageriespring.repository.SignalementRepository;
import com.messageriespring.services.SignalementService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    @DeleteMapping("/article/{articleId}/signalements")
    public void deleteAllSignalementsForArticle(@PathVariable Long articleId) {
        signalementservice.deleteAllSignalementsForArticleId(articleId);
    }
    @PostMapping(value="/savee")
    public Signalement savee(@RequestBody Signalement signalement) {
        String message = signalement.getMessage();
        Date date = signalement.getDate();
        Article article=signalement.getArticle();
        User user = signalement.getUser();
        List<User> users = signalement.getUsers();
        String type = signalement.getType();
        return signalementservice.saveSignalement(message, date, user, users, type,article);
    }

    @GetMapping("/signalements")
    public List<Signalement> getAllSignalement() {
        List<Signalement> signalements = signalementservice.getAllSignalements();
        for (Signalement signalement : signalements) {
            try {
                // Essayer de charger l'article associé au signalement
                Article article = signalement.getArticle();
                // Vérifier si l'article est null
                if (article != null) {
                    // Initialiser l'article et les messages de l'utilisateur de manière explicite
                    Hibernate.initialize(signalement.getArticle());
                    Hibernate.initialize(signalement.getUser().getMessages());
                }
            } catch (EntityNotFoundException ex) {
                // Gérer l'exception EntityNotFoundException
                // Vous pouvez choisir de ne rien faire ou de traiter l'erreur d'une autre manière
                ex.printStackTrace();
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
    public void deleteSignalement(@PathVariable Long id) {
        signalementRepository.deleteById(id);
    }







}
