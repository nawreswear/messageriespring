package com.messageriespring.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date date;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "message_user", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public Message() {
        // Ne charge pas de manière paresseuse, mais de manière précoce
        if (getUser() != null) {
            getUser().getMessages().size();
        }
    }

    public Message(Long id, String message, Date date, User user) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.user = user;
    }

}
