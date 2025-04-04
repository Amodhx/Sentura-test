package com.example.Sentura_test.controller;


import com.example.Sentura_test.dto.UserDTO;
import com.example.Sentura_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String user_id,
                                           @RequestParam(required = false) Boolean trashed){
        UserDTO user = userService.getUser(user_id,trashed);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        UserDTO user = userService.saveUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String user_id,
                                              @RequestBody UserDTO userDTO){

    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> listUsers (@RequestParam Map<String,String> queryParams){

    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String user_id){

    }
}
