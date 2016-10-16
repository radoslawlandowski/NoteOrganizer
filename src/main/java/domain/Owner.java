package domain;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.List;

/**
 * Created by radoslawlandowski on 09.10.16.
 */

@Entity
@Table(name = "owners")
public class Owner implements Principal, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String mail;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    public Owner(){};

    public Owner(String mail, String password) { this.mail = mail; this.password = password; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
