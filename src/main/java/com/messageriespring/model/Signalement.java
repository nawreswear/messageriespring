package com.messageriespring.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
    public Signalement() {
        super();
    }
    public Signalement(Long id, String message, Date date, User user){
        super(id, message, date, user);
    }
    public Signalement(Long id, String message, Date date, User user, List<User> users) {
        super(id, message, date, user, users);
    }
    public Signalement(Long id, String type) {
        this.id = id;
        this.type = type;
    }


}
