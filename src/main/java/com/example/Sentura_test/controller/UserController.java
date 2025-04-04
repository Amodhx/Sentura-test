package com.example.Sentura_test.controller;


import com.example.Sentura_test.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String user_id,
                                           @RequestParam(required = false) Boolean trashed){

    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){

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
