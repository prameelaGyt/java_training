package org.example.api2;

import jakarta.transaction.Transactional;
import org.example.api.Info;
import org.example.api.NotFoundException;
import org.example.api.User;
import org.example.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GroupService
{
    @Autowired
    private GroupRepository groupRepository;
    public List<Info> getAllGroups()
    {
        List<Group> groups=groupRepository.findAll();
        List<Info> groupInfoSet=new ArrayList<>();
        for(Group g: groups)
        {
            groupInfoSet.add(new Info(g.getGid(), g.getName()));
        }
        return groupInfoSet;
        //return groupRepository.findAll();
    }
    public Group createGroup(Group group)
    {
        return groupRepository.save(group);
    }

    public Group updateGroup(int gid, Group group) throws NotFoundException
    {
        if (!groupRepository.existsById(gid))
        {
            throw new NotFoundException("User not found");
        }
        group.setGid(gid);
        return groupRepository.save(group);
    }

    public void deleteGroup(int gid) throws NotFoundException
    {
        if (!groupRepository.existsById(gid))
        {
            throw new NotFoundException("User not found");
        }
        groupRepository.deleteById(gid);
    }

    @Transactional
    public List<Group> createMultipleGroups(List<Group> groups)
    {

        for (Group group : groups)
        {
//            if(group.getName().equals("G3"))
//            {
//                throw new RuntimeException("name invalid");
//            }
            groupRepository.save(group);
        }
        if (groups.size() != 5)
        {
            throw new RuntimeException("Exactly 5 groups are required");
        }
        return groups;
    }
}
