package org.example.api2;

import org.example.api.NotFoundException;
import org.example.api.User;
import org.example.api.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController
{
    @Autowired
    private GroupService groupService;
    @GetMapping
    public List<Info> getAllGroups()
    {
        return groupService.getAllGroups();
    }
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group createdGroup = groupService.createGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }
    @PutMapping("/{gid}")
    public ResponseEntity<Object> updateGroup(@PathVariable("gid") int gid, @RequestBody Group group)
    {
        try
        {
            Group updatedGroup = groupService.updateGroup(gid, group);
            return ResponseEntity.ok(updatedGroup);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(group);
        }
    }
    @DeleteMapping("/{gid}")
    public ResponseEntity<String> deleteGroup(@PathVariable("gid") int gid)
    {
        try
        {
            groupService.deleteGroup(gid);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("record deleted");
        }
        catch(NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createMultipleGroups(@RequestBody List<Group> groups)
    {
        try
        {
            List<Group> createdGroups = groupService.createMultipleGroups(groups);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGroups);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating groups");
        }
    }
}
