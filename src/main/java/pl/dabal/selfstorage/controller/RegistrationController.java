package pl.dabal.selfstorage.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.CurrentUser;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.dto.UserDto;
import pl.dabal.selfstorage.service.StorageService;
import pl.dabal.selfstorage.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration/form";
    }

    @PostMapping
    public String registerUser(@Validated UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "registration/form";
        }
        if (!userDto.getPassword().equals(userDto.getPassword1())) {
            result.addError(new FieldError("userDto", "password", userDto.getPassword(), false, new String[]{"registrationForm.passwordDoesNotMatch"}, null, null));
            model.addAttribute("userDto", userDto);
            return "registration/form";
        }
        if (userService.findByUserName(userDto.getUsername()) != null) {
            result.addError(new FieldError("userDto", "username", userDto.getUsername(), false, new String[]{"registrationForm.usernameIsAlreadyUsed"}, null, null));
            model.addAttribute("userDto", userDto);
            return "registration/form";
        }
        userService.saveUser(userService.convertUserDtoToUser(userDto));
        return "redirect:login";
    }


}
