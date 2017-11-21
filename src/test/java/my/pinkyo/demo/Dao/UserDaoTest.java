package my.pinkyo.demo.Dao;

import io.dropwizard.testing.junit.DAOTestRule;
import my.pinkyo.demo.entity.UserEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class UserDaoTest {
    @Rule
    public DAOTestRule database = DAOTestRule.newBuilder().addEntityClass(UserEntity.class).build();

    private UserDao userDao;
    private UserEntity newEntity;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao(database.getSessionFactory());

        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setSex("MALE");

        newEntity = database.inTransaction(() -> userDao.createUser(userEntity));

        assertThat(newEntity.getId(), notNullValue());
    }

    @Test
    public void updateUser() {
        newEntity.setSex("FEMALE");
        database.inTransaction(() -> userDao.updateUser(newEntity));
    }

    @Test
    public void findByName() {
        assertThat(userDao.findByName(newEntity.getName()), notNullValue());
    }

    @Test
    public void deleteUserByName() {
        database.inTransaction(() -> userDao.deleteUserByName(newEntity.getName()));
    }
}