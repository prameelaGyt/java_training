package org.example.api;

import java.util.HashSet;
import java.util.Set;

public class InfoPrac
{
    private int id;
    private String name;
    private Set<Info> practices=new HashSet<>();
    public InfoPrac(int id, String name, Set<Info> practices)
    {
        this.id = id;
        this.name = name;
        this.practices = practices;
    }
    public Set<Info> getPractices() {
        return practices;
    }

    public void setPractices(Set<Info> practices) {
        this.practices = practices;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
