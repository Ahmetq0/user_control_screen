package com.turkcell.cm.controller.impl;

import com.turkcell.cm.controller.IUserController;
import com.turkcell.cm.dto.DtoUser;
import com.turkcell.cm.dto.DtoUserIU;
import com.turkcell.cm.entity.User;
import com.turkcell.cm.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserControllerIU implements IUserController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/all-list")
    @Override
    public List<DtoUser> getAllListDtoUser() {
        return userService.getAllListDtoUser();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public DtoUser getDtoUserById(@PathVariable(name = "id", required = true) Integer id) {
        return userService.getDtoUserById(id);
    }

    @GetMapping(path = "/with-params")
    @Override
    public List<User> getDtoUserWithParams(@RequestParam(name = "firstName", required = false) String firstName,
                                           @RequestParam(name = "lastName", required = false) String lastName) {
        return userService.getDtoUserWithParams(firstName, lastName);
    }

    @PostMapping(path = "/save")
    @Override
    public DtoUser saveNewUser(@RequestBody DtoUserIU newDtoUserIU) {
        return userService.saveNewUser(newDtoUserIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteUserById(@PathVariable(name = "id", required = true) Integer id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public DtoUser updateUserById(@PathVariable(name = "id", required = true) Integer id,
                                  @RequestBody DtoUserIU dtoUserIU) {
        return userService.updateUserById(id, dtoUserIU);
    }
}
