package org.example.api;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

//    public String getName(String uid,String name)
//    {
//        user.setId(uid);
//        user.setName(name);
//        return " CHanged " + user;
//    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
class NotFoundException extends RuntimeException
{
    public NotFoundException(String msg)
    {
        super(msg);
    }
}
