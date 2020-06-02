package pl.dabal.selfstorage.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.model.Role;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.UserDto;
import pl.dabal.selfstorage.repository.RoleRepository;
import pl.dabal.selfstorage.repository.UserRepository;
import pl.dabal.selfstorage.service.EmailComposerService;
import pl.dabal.selfstorage.service.EmailSenderService;
import pl.dabal.selfstorage.service.StorageService;
import pl.dabal.selfstorage.service.UserService;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailComposerService emailComposerService;
    private final EmailSenderService emailSenderService;
    private final StorageService storageService;


    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findEnabledUserByUserName(String name) {
        return userRepository.findByUsernameAndEnabled(name, 1);
    }

    @Override
    public void activateUser(User user) {
        user.setEnabled(1);
        Storage storage = storageService.createStorage(user, null);
        storageService.save(storage);
        // user.getStorages().add(storage);
        userRepository.save(user);
    }

    @Override
    public void registerUser(User user, Locale locale, String link) {
        String token = UUID.randomUUID().toString().replace("-", "");
        emailSenderService.sendMail(
                user.getUsername(),
                emailComposerService.getSubject("registrationEmail.subject", null, locale),
                emailComposerService.getMessage("registrationEmail.message", new Object[]{link + token}, locale)
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        user.setToken(token);
        Role userRole = roleRepository.findByName("ROLE_REGISTER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User convertUserDtoToUser(UserDto userDto) {
        return User.builder().username(userDto.getUsername()).password(userDto.getPassword()).build();
    }

    @Override
    public User findUserByToken(String token) {
        return userRepository.findByTokenAndEnabled(token, 0);
    }
}