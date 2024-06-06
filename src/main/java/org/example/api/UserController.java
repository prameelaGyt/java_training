package org.example.api;

//import org.example.database.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;
//    @Autowired

//    public UserController(UserService userService) {
////        this.userService = userService;
//    

    @GetMapping("/{uid}")
    public String getUserByUid(@PathVariable("uid") String uid) {
        return "Hello " + userService.getName(uid);
//        Optional<User> user = userService.getUserByUid(uid);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }
}

