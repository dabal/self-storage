package pl.dabal.selfstorage.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.UserDto;
import pl.dabal.selfstorage.service.UserService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void userShoulSeeRegistrationFormWithEmptyModelAndView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration/form"))
                .andExpect(model().attributeExists("userDto"));
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,username", "jan.kowalski@mail.com,1,2,password"})
    public void userShouldNotRegisterWhenBadDataAreSent(String username, String password, String password1, String fieldError) throws Exception {
        UserDto userDto = UserDto.builder().username(username).password(password).password1(password1).build();
        mockMvc.perform(
                post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", username)
                        .param("password", password)
                        .param("password1", password1)
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("registration/form"))
                .andExpect(model().attributeExists("userDto"))
                .andExpect(model().attributeHasFieldErrors("userDto", fieldError));
    }

    @Test
    public void userWithExistingEmailShouldSeeError() throws Exception {
        String username = "Jan@kowalski.com";
        when(userService.findByUserName(username)).thenReturn(new User());
        mockMvc.perform(
                post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", username)
                        .param("password", "password")
                        .param("password1", "password")
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("registration/form"))
                .andExpect(model().attributeExists("userDto"))
                .andExpect(model().attributeHasFieldErrors("userDto", "username"));
        verify(userService, times(1)).findByUserName(username);
    }

    @Test
    public void userWithValidDataShouldBeRegistered() throws Exception {
        when(userService.convertUserDtoToUser(any(UserDto.class))).thenReturn(new User());
        when(userService.findByUserName(anyString())).thenReturn(null);
        //when(userService.saveUser(any(User)))
        mockMvc.perform(
                post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "jan@kowalski.com")
                        .param("password", "password")
                        .param("password1", "password")
                        .with(csrf())
        )
                .andExpect(status().is(302))
                .andExpect(redirectedUrlPattern("**/login/"));
        verify(userService).saveUser(any(User.class));

    }

}