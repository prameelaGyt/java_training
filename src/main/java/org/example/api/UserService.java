package org.example.api;

import java.util.*;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.example.api2.Group;
import org.example.api2.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
    public List<Info> getUsersByPagination(int page, int size,String sort_by,String direction,String search)throws Exception
    {
        Sort sort=Sort.by(Sort.Direction.fromString(direction),sort_by);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAllWithSearch(search,pageable);

        List<Info> userInfoSet = new ArrayList<>();
        for (User u : userPage)
        {
            userInfoSet.add(new Info(u.getId(), u.getName()));
        }
        return userInfoSet;
    }

    public Object createUser(User user) throws NotFoundException
    {
        try {
            return userRepository.save(user);
        }
        catch (EntityExistsException e) {
        /*
           This exception occurs when we try to persist a user which is already present in the database
         */
            //throw new NotFoundException("User already exists",e);
            return e;
        }
        catch (DataIntegrityViolationException e) {
        /*
           This exception occurs usually when we violate the constraints of a database table...
           for example that we define the email field of a table as UNIQUE KEY...and now if we try
           to add  an email which is already present then we get DataIntegrityViolationException
           because for a UNIQUE key field cannot contain duplicate values

         */
            //throw new NotFoundException("Data integrity violation", e);
            return e;
        }
        catch (IllegalArgumentException e) {
        /*
         This exception usually occurs when we pass parameters which are not in range or not in an expected
         format or if we pass negative value for a positive value requirement
        */
           // throw new NotFoundException("Illegal argument", e);
            return e;
        }
        catch (Exception e) {
          //  throw new NotFoundException("An unexpected error occurred", e);
            return e;
        }
    }

    public User updateUser(int uid, User user) throws NotFoundException
    {
        if (!userRepository.existsById(uid))
        {
            throw new NotFoundException("User not found");
        }
        user.setId(uid);
        return userRepository.save(user);
    }

    public void deleteUser(int uid) throws NotFoundException
    {
        if (!userRepository.existsById(uid))
        {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(uid);
    }
  //  @Transactional
    public void assignGroupToUser(int userId, int groupId) throws NotFoundException
    {
//        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
//        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
//        user.getGroups().add(group);//automatically added in  users set
//        userRepository.save(user);
//        return user;
        int updatedRows = userRepository.addUserToGroupIfExists(userId,groupId);

        if (updatedRows == 0) {
            throw new NotFoundException("User or Group not found");
        }

        //return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found after update"));
    }
    public void removeGroupFromUser(int userId, int groupId) throws NotFoundException
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));//2 selects and 1 update can replace plain update
        user.getGroups().remove(group);
        userRepository.save(user);
        //return user;
    }

    public Set<Info> getGroupsByUserId(int userId) throws NotFoundException
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
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
        return  userInfoSet;
    }
}