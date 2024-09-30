package com.turkcell.cm.services.impl;

import com.turkcell.cm.dto.DtoUser;
import com.turkcell.cm.dto.DtoUserIU;
import com.turkcell.cm.entity.User;
import com.turkcell.cm.repository.IUserRepository;
import com.turkcell.cm.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<DtoUser> getAllListDtoUser() {
        List<DtoUser> allUserList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            DtoUser findAllUsers = new DtoUser();
            BeanUtils.copyProperties(user, findAllUsers);
            allUserList.add(findAllUsers);
        }
        return allUserList;
    }

    @Override
    public DtoUser getDtoUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        DtoUser dtoUser = new DtoUser();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BeanUtils.copyProperties(user, dtoUser);
        }
        return dtoUser;
    }

    @Override
    public List<User> getDtoUserWithParams(String firstName, String lastName) {
        List<User> findUserWithParams = new ArrayList<>();
        if (firstName == null && lastName == null) {
            return userRepository.findAll();
        }
        for (User user : userRepository.findAll()) {
            if (firstName != null && lastName != null) {
                if (firstName.equalsIgnoreCase(user.getFirstName()) && lastName.equalsIgnoreCase(user.getLastName())) {
                    findUserWithParams.add(user);
                }
            }
            if (firstName != null && lastName == null) {
                if (firstName.equalsIgnoreCase(user.getFirstName())) {
                    findUserWithParams.add(user);
                }
            }
            if (lastName != null && firstName == null) {
                if (lastName.equalsIgnoreCase(user.getLastName())) {
                    findUserWithParams.add(user);
                }
            }
        }
        return findUserWithParams;
    }

    @Override
    public DtoUser saveNewUser(DtoUserIU newDtoUserIU) {
        DtoUser response = new DtoUser();
        User user = new User();
        BeanUtils.copyProperties(newDtoUserIU, user);
        User dbNewSaveUser = userRepository.save(user);
        BeanUtils.copyProperties(dbNewSaveUser, response);

        return response;
    }

    @Override
    public void deleteUserById(Integer id) {
        Optional<User> optionalFindDeleteUserById = userRepository.findById(id);
        optionalFindDeleteUserById.ifPresent(user -> userRepository.delete(user));
    }

    @Override
    public DtoUser updateUserById(Integer id, DtoUserIU updateDtoUserIU) {
        Optional<User> optionalFindUpdateUserById = userRepository.findById(id);
        if (optionalFindUpdateUserById.isPresent()) {
            User dataBaseUser = optionalFindUpdateUserById.get();
            dataBaseUser.setFirstName(updateDtoUserIU.getFirstName());
            dataBaseUser.setLastName(updateDtoUserIU.getLastName());
            userRepository.save(dataBaseUser);

            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(dataBaseUser, dtoUser);
            return dtoUser;
        }
        return null;
    }
}