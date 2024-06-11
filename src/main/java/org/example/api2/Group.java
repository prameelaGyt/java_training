package org.example.api2;

import jakarta.persistence.*;
import org.example.api.Info;
import org.example.api.User;
import org.example.api3.Practice;

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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Practice> practices = new HashSet<>();


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
//        Set<Info> userSet=new HashSet<>();
//        for(User u: users)
//        {
//            userSet.add(new Info(u.getId(),u.getName()));
//        }
//        return userSet;
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public Set<Practice> getPractices()
    {
//        Set<Info> practiceSet=new HashSet<>();
//        for(Practice p: practices)
//        {
//            practiceSet.add(new Info(p.getId(),p.getName()));
//        }
//        return practiceSet;
        return practices;
    }

    public void setPractices(Set<Practice> practices) {
        this.practices = practices;
    }

    @Override
    public String toString()
    {
        return gid + ": " + name;
    }
}
