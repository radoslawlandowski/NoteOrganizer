package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by radoslawl on 25/07/16.
 */

@Entity
@Table(name = "tabs", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "owner"}))
@NamedQueries({
        @NamedQuery(name = "Tab.all", query = "select t from Tab t where t.owner = :owner"),
        @NamedQuery(name = "Tab.delete", query = "delete Tab t where t.id = :id"),
        @NamedQuery(name = "Tab.get", query = "delete Tab t where t.name = :name AND t.owner = :owner")
})
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Tab() {}

    public Tab(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tab tab = (Tab) o;

        if (id != null ? !id.equals(tab.id) : tab.id != null) return false;
        if (name != null ? !name.equals(tab.name) : tab.name != null) return false;
        return owner != null ? owner.equals(tab.owner) : tab.owner == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
