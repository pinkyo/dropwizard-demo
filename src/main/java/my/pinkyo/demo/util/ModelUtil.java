package my.pinkyo.demo.util;

import my.pinkyo.demo.entity.UserEntity;
import my.pinkyo.demo.model.User;

/**
 * Created by pinkyo on 2017/7/9.
 */
public class ModelUtil {

    public static UserEntity convertToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setSex(user.getSex());
        return entity;
    }

    public static User convertToModel(UserEntity entity) {
        User user = new User();
        user.setName(entity.getName());
        user.setSex(entity.getSex());

        return user;
    }
}