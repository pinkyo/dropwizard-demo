package my.pinkyo.demo.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import my.pinkyo.demo.Dao.UserDao;
import my.pinkyo.demo.entity.UserEntity;
import my.pinkyo.demo.model.User;
import my.pinkyo.demo.util.ModelUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @POST
    @Timed
    @UnitOfWork
    public Response createUser(@Valid @NotNull User user) {
        UserEntity entity = userDao.findByName(user.getName());
        if (entity != null) {
            throw new RuntimeException("user have existed.");
        }

        URI createdUri = URI.create(String.format("/test/%s", user.getName()));
        UserEntity result = userDao.createUser(ModelUtil.convertToEntity(user));
        return Response.created(createdUri).entity(ModelUtil.convertToModel(result)).build();
    }

    @PUT
    @Timed
    @UnitOfWork
    public Response updateUser(@Valid @NotNull User user) {
        UserEntity entity = userDao.findByName(user.getName());
        if (entity == null) {
            throw new RuntimeException("user is not found.");
        }

        userDao.updateUser(ModelUtil.convertToEntity(user));
        return Response.noContent().build();
    }

    @GET
    @Path("/{name}")
    @Timed
    @UnitOfWork
    public User getUserByName(@PathParam("name") String name) {
        return ModelUtil.convertToModel(userDao.findByName(name));
    }

    @DELETE
    @Path("/{name}")
    @Timed
    @UnitOfWork
    public Response deleteUserByName(@PathParam("name") String name) {
        userDao.deleteUserByName(name);
        return Response.noContent().build();
    }
}
