package org.example.api;

import java.util.*;

import org.example.api2.Group;
import org.example.api2.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
//    public String getName(String uid,String name)
//    {
//        user.setId(uid);
//        user.setName(name);
//        return " CHanged " + user;
//    }
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public User updateUser(int uid, User user) throws NotFoundException
    {
        if (!userRepository.existsById(String.valueOf(uid)))
        {
            throw new NotFoundException("User not found");
        }
        user.setId(uid);
        return userRepository.save(user);
    }

    public void deleteUser(int uid) throws NotFoundException
    {
        if (!userRepository.existsById(String.valueOf(uid)))
        {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(String.valueOf(uid));
    }

    public User assignGroupToUser(int userId, int groupId) throws NotFoundException
    {
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new NotFoundException("User not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
        user.getGroups().add(group);
        group.getUsers().add(user);
        userRepository.save(user);
        return user;
    }
    public User removeGroupFromeUser(int userId, int groupId) throws NotFoundException
    {
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new NotFoundException("User not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
        user.getGroups().remove(group);
        group.getUsers().remove(user);
        userRepository.save(user);
        return user;
    }

    public Set<Info> getGroupsByUserId(int userId) throws NotFoundException
    {
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new NotFoundException("User not found"));
        Set<Group> groups=user.getGroups();
        Set<Info> groupInfoSet=new HashSet<>();
        for(Group g: groups)
        {
            groupInfoSet.add(new Info(g.getGid(), g.getName()));
        }
        return groupInfoSet;
    }

    public Set<Info> getUsersByGroupId(int groupId) throws NotFoundException
    {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
        Set<User> users=group.getUsers();
        Set<Info> userInfoSet=new HashSet<>();
        for(User u:users)
        {
            userInfoSet.add(new Info(u.getId(), u.getName()));
        }
        return userInfoSet;
    }
}