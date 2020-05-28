package pl.dabal.selfstorage.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import pl.dabal.selfstorage.model.*;
import pl.dabal.selfstorage.security.SecurityConfig;
import pl.dabal.selfstorage.service.SpringDataUserDetailsService;
import pl.dabal.selfstorage.service.StorageService;
import pl.dabal.selfstorage.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StorageController.class)
@Import(SecurityConfig.class)
class StorageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private SpringDataUserDetailsService springDataUserDetailsService;

    CurrentUser currentUser;


    @BeforeEach
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        //roles.add(Role.builder().name("ROLE_USER").build());
        User user = User.builder().username("username").password("password").roles(roles).build();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        currentUser = new CurrentUser(user.getUsername(), user.getPassword(), grantedAuthorities, user);
    }

    @Test
    public void userWithoutAuthenticationShouldBeRedirectedToLoginForm() throws Exception {
        mockMvc.perform(get("/storage/details?id=1")).andExpect(status().is(302)).andExpect(redirectedUrlPattern("**/login/"));
    }

    @Test
    public void authenticatedUserShouldSeeDetailsOfHisStorage() throws Exception {
        when(storageService.getStorageByIdAndUser(anyLong(), any(User.class))).thenAnswer(i -> Optional.of(Storage.builder().user((User) i.getArguments()[1]).name("test").build()));
        when(storageService.getItemsForStorage(any(Storage.class))).thenReturn(new ArrayList<Item>());
        mockMvc.perform(get("/storage/details?id=1").with(user(currentUser))).andExpect(status().isOk());

    }

    @Test
    public void authenticatedUserShouldNotSeeDetailsOfNotHisStorage() throws Exception {
        when(storageService.getStorageByIdAndUser(anyLong(), any(User.class))).thenAnswer(i -> Optional.ofNullable(null));
        mockMvc.perform(get("/storage/details?id=1").with(user(currentUser))).andExpect(status().is(404));

    }
}