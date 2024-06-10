package org.example.api;

//import org.example.database.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.example.api2.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<Info> getAllUsers()
    {
        //hello
        return userService.getAllUsers();
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        try
        {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id)
    {
        try
        {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("record deleted");
        }
        catch(NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
        }
    }

    @PostMapping("/{uid}/{gid}")
    public ResponseEntity<Object> assignGroupToUser(@PathVariable("uid") int uid, @PathVariable("gid") int gid)
    {
        try
        {
            User user = userService.assignGroupToUser(uid, gid);
            return ResponseEntity.ok("Assigned" +
                    "");
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
        }
    }
    @DeleteMapping("/{uid}/{gid}")
    public ResponseEntity<String> removeGroupFromUSer(@PathVariable("uid") int uid, @PathVariable("gid") int gid)
    {
        try
        {
            userService.removeGroupFromeUser(uid,gid);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("record deleted");
        }
        catch(NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
        }
    }
    @GetMapping("/{uid}/")
    public /*ResponseEntity<Set<Info>>*/ ResponseEntity<?> getGroupsByUserId(@PathVariable("uid") int uid)
    {
        try
        {
           // Set<Info> groups = userService.getGroupsByUserId(uid);
            User user=userService.getGroupsByUserId(uid);
            return ResponseEntity.ok(user);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{gid}")
    public ResponseEntity<Set<Info>> getUsersByGroupId(@PathVariable("gid") int gid)
    {
        try
        {
            Set<Info> users = userService.getUsersByGroupId(gid);
            return ResponseEntity.ok(users);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

}

