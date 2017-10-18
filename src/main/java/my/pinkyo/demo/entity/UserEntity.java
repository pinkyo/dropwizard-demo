package my.pinkyo.demo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by yinkn on 2017/7/9.
 */
@Entity
@Table(name = "user", indexes = @Index(columnList = "name", unique = true))
@NamedQueries(
        @NamedQuery(name = "findByName",
                query = "SELECT u FROM UserEntity u WHERE u.name=:name"))
@GenericGenerator(name = "system-uuid", strategy = "uuid2")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;

    private String sex;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(sex, entity.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex);
    }
}