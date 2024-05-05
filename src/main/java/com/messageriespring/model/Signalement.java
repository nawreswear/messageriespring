package com.messageriespring.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Signalement extends Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String type;

    public Signalement() {
        super();
    }
    public Signalement(Long id, String message, Date date, User user){
        super(id, message, date, user);
    }
    public Signalement(Long id, String message, Date date, User user, List<User> users) {
        super(id, message, date, user, users);
    }

}
