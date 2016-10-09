package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by radoslawl on 25/07/16.
 */

@Entity
@Table(name = "notes")
@NamedQueries({
        @NamedQuery(name = "Note.all", query = "select n from Note n where n.owner = :owner"),
        @NamedQuery(name = "Note.delete", query = "delete Note n where n.id = :id")
})
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content = "";

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    @Column(name = "tab")
    private String tab;

    public Note() {}

    public Note(String title, String content, Date date, Owner owner, String tab) {
        this.title = title;
        this.content = content;
        this.owner = owner;
        this.date = date;
        this.tab = tab;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty
    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
