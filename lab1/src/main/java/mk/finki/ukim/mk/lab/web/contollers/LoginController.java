package mk.finki.ukim.mk.lab.web.contollers;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    public LoginController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping(value = {"/login", "/"})
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/events/all";
        }
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (authService.authenticate(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/events/all";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "username already exists !");
            return "redirect:/login";
        }
        userService.addUser(new User(username, password));
        session.setAttribute("username", username);
        return "redirect:/events/all";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }
}