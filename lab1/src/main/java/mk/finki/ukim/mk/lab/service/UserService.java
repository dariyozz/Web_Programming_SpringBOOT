package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void addUser(User user);

    void removeUser(User user);

    Set<User> getLoggedUsers();

    Optional<User> findByUsername(String username);
}
