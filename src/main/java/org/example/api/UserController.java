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

    @GetMapping({"/{page}/{size}/{sort_by}/{direction}/{search}", "/{page}/{size}/{sort_by}/{direction}", "/{page}/{size}/{sort_by}", "/{page}/{size}", "/{page}",""})
    public List<Info> getUsers(@PathVariable(value = "page",required = false) Integer page,@PathVariable(value="size",required=false) Integer size,@PathVariable(value="sort_by",required=false) String sort_by,@PathVariable(value = "direction",required=false) String direction,@PathVariable(value = "search",required=false) String search) throws Exception
    {
        if (page==null)
        {
            page = 0;
        }
        if (size == null)
        {
            size = 4;
        }
        if (sort_by == null)
        {
            sort_by = "uid";
        }
        if(direction==null)
        {
            direction="ASC";
        }
//        if(direction.equals("ASC") ||direction.equals("DESC"))
//        {
//            direction="ASC";
//        }
        return userService. getUsersByPagination(page, size,sort_by,direction,search);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception
    {
        Object createdUser = userService.createUser(user);
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
            userService.assignGroupToUser(uid, gid);
            return ResponseEntity.ok("Assigned");
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
            Set<Info> groups = userService.getGroupsByUserId(uid);
            return ResponseEntity.ok(groups);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gid/{gid}")
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

