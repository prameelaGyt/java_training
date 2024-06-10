package org.example.api;

import jakarta.persistence.*;
import org.example.api2.Group;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    //@Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "gid")
    )
    private Set<Group> groups=new HashSet<>();
    public User() {}

    public User(int uid, String name)
    {
        this.uid =uid;
        this.name = name;
    }
    public int getId()
    {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }//this method invoked internally even if we mention as auto increment

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString()
    {
        return uid + ": " + name;
    }
}
