package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.enums.Role;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void register(String username, String password, String repeatPassword, String name, Role role);

    void removeUser(User user);

    Set<User> getLoggedUsers();

    Optional<User> findByUsername(String username);
}
