package com.turkcell.cm.services;

import com.turkcell.cm.dto.DtoUser;
import com.turkcell.cm.dto.DtoUserIU;
import com.turkcell.cm.entity.User;

import java.util.List;

public interface IUserService {
    public List<DtoUser> getAllListDtoUser();
    public DtoUser getDtoUserById(Integer id);
    public List<User> getDtoUserWithParams(String firstName, String lastName);
    public DtoUser saveNewUser(DtoUserIU newDtoUserIU);
    public void deleteUserById(Integer id);
    public DtoUser updateUserById(Integer id, DtoUserIU updateDtoUserIU);
}
