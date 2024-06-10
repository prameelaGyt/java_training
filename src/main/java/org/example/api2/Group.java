package org.example.api2;

import jakarta.persistence.*;
import org.example.api.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="grp")
public class Group
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;
    private String name;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users=new HashSet<>();
    public Group() {}
    public Group(int gid,String name)
    {
        this.gid=gid;
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getGid()
    {
        return gid;
    }

    public void setGid(int gid)
    {
        this.gid = gid;
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return gid + ": " + name;
    }
}
