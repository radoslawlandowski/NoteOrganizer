package DAO;

import domain.Note;
import domain.Owner;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by radoslawl on 25/07/16.
 */
public class NoteDAO extends AbstractDAO<Note> {

    @Inject
    public NoteDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Note> all(Owner owner) {
        return list(namedQuery("Note.all").setParameter("owner", owner));
    }

    public Note create(Note n) {
        return persist(n);
    }

    public int delete(long id) {
        return namedQuery("Note.delete").setParameter("id", id).executeUpdate();
    }
}
