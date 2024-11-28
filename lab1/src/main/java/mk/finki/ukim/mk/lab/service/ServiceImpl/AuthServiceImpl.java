package mk.finki.ukim.mk.lab.service.ServiceImpl;


import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticate(String username, String password) {
        Optional<User> user = userService.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
