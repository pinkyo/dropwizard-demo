package my.pinkyo.demo.Dao;

import io.dropwizard.hibernate.AbstractDAO;
import my.pinkyo.demo.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
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

    @Valid
    public UserEntity createUser(@NotNull UserEntity user) {
        return persist(user);
    }

    @Valid
    public void updateUser(@NotNull UserEntity user) {
        UserEntity entity = findByName(user.getName());
        entity.setSex(user.getSex());
        currentSession().update(entity);
    }

    @Valid
    public UserEntity findByName(@NotEmpty String name) {
        return uniqueResult(
                namedQuery("findByName")
                        .setParameter("name", name));
    }

    @Valid
    public void deleteUserByName(@NotEmpty String name) {
        UserEntity user = findByName(name);
        currentSession().delete(user);
    }
}
