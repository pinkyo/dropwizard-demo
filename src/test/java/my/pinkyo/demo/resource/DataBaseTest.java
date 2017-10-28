package my.pinkyo.demo.resource;

import io.dropwizard.testing.junit.DAOTestRule;
import my.pinkyo.demo.Dao.UserDao;
import my.pinkyo.demo.entity.UserEntity;
import my.pinkyo.demo.model.Sex;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class DataBaseTest {
    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(UserEntity.class)
            .build();

    private UserDao dao;
    private final String name = "test";
    private final String sex = Sex.MALE.toString();

    @Before
    public void setUp() throws Exception {
        dao = new UserDao(daoTestRule.getSessionFactory());
    }

    @Test
    public void testCreateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setSex(sex);

        UserEntity result = daoTestRule.inTransaction(
                () -> dao.createUser(userEntity));
        assertThat(result.getId(), notNullValue());
    }
}
