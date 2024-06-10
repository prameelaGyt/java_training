package org.example.api2;

import org.example.api.NotFoundException;
import org.example.api.User;
import org.example.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupService
{
    @Autowired
    private GroupRepository groupRepository;
    public List<Group> getAllGroups()
    {
        return groupRepository.findAll();
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
}
