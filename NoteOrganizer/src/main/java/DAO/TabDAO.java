package DAO;

import domain.Tab;
import domain.Owner;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by radoslawl on 25/07/16.
 */
public class TabDAO extends AbstractDAO<Tab> {

    public TabDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Tab> all(Owner owner) {
        return list(namedQuery("Tab.all").setParameter("owner", owner));
    }

    public Tab create(Tab t) {
        return persist(t);
    }

    public Tab get(String name, Owner owner) {
        return uniqueResult(namedQuery("Tab.get").setParameter("name", name).setParameter("owner", owner));
    }

    public int deleteTab(long id) {
        return namedQuery("Tab.delete").setParameter("id", id).executeUpdate();
    }
}
