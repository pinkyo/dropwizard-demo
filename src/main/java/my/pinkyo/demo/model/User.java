package my.pinkyo.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @Size(min = 1, max = 90)
    private String name;
    @NotNull
    private Sex sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
