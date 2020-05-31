package pl.dabal.selfstorage.service;

import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.UserDto;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    User convertUserDtoToUser(UserDto userDto);
}  