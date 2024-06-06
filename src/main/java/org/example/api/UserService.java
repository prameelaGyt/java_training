package org.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

//    @Autowired
    private User user;

    public String getName(String name) {
        return " CHanged " + new User("1", name);
    }
}
