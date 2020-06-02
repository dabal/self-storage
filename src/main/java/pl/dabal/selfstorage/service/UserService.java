package pl.dabal.selfstorage.service;

import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.UserDto;

import java.util.Locale;

public interface UserService {

    User findByUserName(String name);

    User findEnabledUserByUserName(String name);

    void activateUser(User user);

    void registerUser(User user, Locale locale, String link);


    User convertUserDtoToUser(UserDto userDto);

    User findUserByToken(String token);

}  