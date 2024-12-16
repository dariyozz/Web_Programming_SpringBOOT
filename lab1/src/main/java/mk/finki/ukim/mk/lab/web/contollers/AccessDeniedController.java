package mk.finki.ukim.mk.lab.web.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {


    @GetMapping
    public String access_denied(){
        return "access_denied";
    }
}
