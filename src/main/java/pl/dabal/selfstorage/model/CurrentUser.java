package pl.dabal.selfstorage.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final pl.dabal.selfstorage.model.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.dabal.selfstorage.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.dabal.selfstorage.model.User getUser() {
        return user;
    }
}