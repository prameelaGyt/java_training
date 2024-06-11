package org.example.api3;

import org.example.api2.Group;
import jakarta.persistence.*;

@Entity
@Table(name = "practice")
public class Practice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;

    @ManyToOne
    @JoinColumn(name = "gid")
    private Group group;

    public Practice() {}

    public Practice(int id, String name)
    {
        this.pid = id;
        this.name = name;
        this.group = group;
    }

    public int getId()
    {
        return pid;
    }

    public void setId(int id)
    {
        this.pid = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return pid + ": " + name;
    }
}
