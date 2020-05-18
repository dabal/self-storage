package pl.dabal.selfstorage.service;

import pl.dabal.selfstorage.model.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}  