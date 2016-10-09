package resources;

import DAO.TabDAO;
import com.google.inject.Inject;
import domain.Owner;
import domain.Tab;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by radoslawl on 26/07/16.
 */

@Path("/tabs")
@Produces(MediaType.APPLICATION_JSON)
public class TabResource {

    private TabDAO dao;

    @Path("/create")
    @PUT
    @UnitOfWork
    public Response create(Tab tab, @Auth Owner owner) {
        tab.setOwner(owner);
        Tab created;
        Response r;

        if(dao.get(tab.getName(), tab.getOwner()) == null) {
            created = dao.create(tab);
            r = Response.status(Response.Status.CREATED).entity(created).build();
        } else {
            r = Response.status(Response.Status.CONFLICT).entity(tab).build();
        }
        return r;
    }

    @Inject
    public TabResource(TabDAO dao) {
        this.dao = dao;
    }

    @Path("/all")
    @GET
    @UnitOfWork
    public Response all(@Auth Owner owner) {
        return Response.status(Response.Status.OK).entity(dao.all(owner)).build();
    }

    @Path("/delete")
    @DELETE
    @UnitOfWork
    public Response delete(@QueryParam("id") long id, @Auth Owner owner) {
        return Response.status(Response.Status.OK).entity(dao.deleteTab(id)).build();
    }
}
