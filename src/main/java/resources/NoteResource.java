package resources;

import DAO.NoteDAO;
import domain.Note;
import domain.Owner;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by radoslawl on 26/07/16.
 */

@Path("notes")
@Produces(MediaType.APPLICATION_JSON)
public class NoteResource {

    private NoteDAO dao;

    public NoteResource(NoteDAO dao) {
        this.dao = dao;
    }

    @Path("/create")
    @PUT
    @UnitOfWork
    public Response create(Note note, @Auth Owner owner) {
        note.setOwner(owner);
        Note created = dao.create(note);
        return Response.status(Response.Status.CREATED).entity(created).build();
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
        return Response.status(Response.Status.OK).entity(dao.delete(id)).build();
    }
}
