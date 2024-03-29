package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    private List<User> getAllUsers() {
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(userService.getUsersBetweenLevels(1, 100));
        modelData.setUsers(getAllUsers());
    }

    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    public void deleteUserById(long id) {
        User user = userService.deleteUser(id);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    public void changeUserData(String name, long id, int level)
    {
        User user = userService.createOrUpdateUser(name, id, level);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    private List<User> getActiveUsers(List<User> userList) {
        return userService.filterOnlyActiveUsers(userList);
    }
}