package org.example.api3;

import org.example.api.Info;
import org.example.api.NotFoundException;
import org.example.api2.Group;
import org.example.api2.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/practices")
public class PracticeController
{
    @Autowired
    private PracticeService practiceService;
    @GetMapping
    public Set<Info> getAllPractice()
    {
        return practiceService.getAllPractices();
    }
    @PostMapping("/{gid}")
    public ResponseEntity<Practice> createPractice(@RequestBody Practice practice,@PathVariable("gid") int gid) throws NotFoundException
    {
        Practice createdPractice = practiceService.createPractice(practice,gid);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPractice);
    }
    @PutMapping("/{pid}")
    public ResponseEntity<Object> updatePractice(@PathVariable("pid") int pid, @RequestBody Practice practice)
    {
        try
        {
            Practice updatedPractice = practiceService.updatePractice(pid, practice);
            return ResponseEntity.ok(updatedPractice);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(practice);
        }
    }
    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteGroup(@PathVariable("pid") int pid)
    {
        try
        {
            practiceService.deletePractice(pid);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("record deleted");
        }
        catch(NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("record not found");
        }
    }
}
