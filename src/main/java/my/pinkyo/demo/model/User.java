package my.pinkyo.demo.model;

import javax.validation.constraints.Size;

public class User {
    @Size(min = 1, max = 90)
    private String name;
    @Size(min = 1, max = 20)
    private String sex;

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
}
