package org.example.api;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User
{

    @Id
    private String uid;
    //@Column(name = "name")
    private String name;

    public User() {}

    public User(String uid, String name)
    {
        this.uid =uid;
        this.name = name;
    }
    public String getId()
    {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return uid + ": " + name;
    }
}
