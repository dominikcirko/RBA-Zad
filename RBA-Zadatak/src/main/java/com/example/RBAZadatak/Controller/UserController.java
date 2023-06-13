package com.example.RBAZadatak.Controller;

import com.example.RBAZadatak.Entity.User;
import com.example.RBAZadatak.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{oib}")
    public ResponseEntity<User> findByOib(@PathVariable String oib){
        return new ResponseEntity<>(userService.findByOib(oib), HttpStatus.FOUND);
    }

    @DeleteMapping("/{oib}")
    public ResponseEntity<User> deleteByOib(@PathVariable String oib){
        userService.deleteByOib(oib);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
