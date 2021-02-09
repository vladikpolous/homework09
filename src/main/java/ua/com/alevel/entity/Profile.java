package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Profile extends AbstractEntity{
    private String name;
    private String email;
    private int age;

    public Profile() {
        super();
    }
    public Profile(String name, String email) {
        super();
        setName(name);
        setEmail(email);
    }
}
