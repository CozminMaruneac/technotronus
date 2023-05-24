package com.usv.technotronus.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/bulk")
    public List<UserDto> createUsers(@RequestBody List<UserDto> users) {
        return userService.createUsersBulk(users);

    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(name = "id") UUID userId) {
        return userService.getById(userId);

    }

    @GetMapping("")
    public UserDto getCurrentUser(){

        return userService.getCurrentUser();
    }
}
