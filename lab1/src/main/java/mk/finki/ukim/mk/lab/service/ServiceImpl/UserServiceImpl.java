package mk.finki.ukim.mk.lab.service.ServiceImpl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> getLoggedUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
