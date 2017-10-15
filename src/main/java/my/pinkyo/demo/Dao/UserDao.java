package my.pinkyo.demo.Dao;

import io.dropwizard.hibernate.AbstractDAO;
import my.pinkyo.demo.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDao extends AbstractDAO<UserEntity> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public UserEntity createUser(@NotNull UserEntity user) {
        return persist(user);
    }

    public void updateUser(@NotNull UserEntity user) {
        UserEntity entity = findByName(user.getName());
        entity.setSex(user.getSex());
        currentSession().update(entity);
    }

    public UserEntity findByName(@NotEmpty String name) {
        return uniqueResult(
                namedQuery("findByName")
                        .setParameter("name", name));
    }

    public void deleteUserByName(@NotEmpty String name) {
        UserEntity user = findByName(name);
        currentSession().delete(user);
    }
}
