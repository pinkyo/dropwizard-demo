package my.pinkyo.demo.resource;

import io.dropwizard.testing.junit.ResourceTestRule;
import my.pinkyo.demo.Dao.UserDao;
import my.pinkyo.demo.entity.UserEntity;
import my.pinkyo.demo.model.Sex;
import my.pinkyo.demo.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserResourceTest {
    private static final UserDao dao = mock(UserDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UserResource(dao))
            .build();

    private String name = "test";
    private UserEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new UserEntity();
        entity.setName(name);
        entity.setSex(Sex.MALE.name());
        doReturn(entity).when(dao).createUser(eq(entity));
        doNothing().when(dao).updateUser(eq(entity));
        doNothing().when(dao).deleteUserByName(eq(name));
    }

    @After
    public void tearDown() throws Exception {
        reset(dao);
    }

    @Test
    public void createUser() {
        User user = new User(name, Sex.MALE);
        Entity<User> content = Entity.json(user);
        Response result = resources.target("/test").request().post(content);
        verify(dao).createUser(entity);
        assertThat(result.getStatus()).isEqualTo(201);
    }

    @Test
    public void updateUser() {
        doReturn(entity).when(dao).findByName(eq(name));

        User user = new User(name, Sex.MALE);
        Entity<User> content = Entity.json(user);
        Response result = resources.target("/test").request().put(content);
        verify(dao).updateUser(entity);
        assertThat(result.getStatus()).isEqualTo(204);
    }

    @Test
    public void getUserByName() {
        doReturn(entity).when(dao).findByName(eq(name));

        User result = resources.target("/test/" + name)
                .request().get(User.class);
        assertThat(result).isEqualTo(new User(name, Sex.MALE));
    }

    @Test
    public void deleteUserByName() {
        doReturn(entity).when(dao).findByName(eq(name));

        Response result = resources.target("/test/" + name)
                .request().delete();
        assertThat(result.getStatus()).isEqualTo(204);
    }
}