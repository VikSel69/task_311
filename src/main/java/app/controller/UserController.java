package app.controller;

import app.model.User;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("")
    public String getUserPage(@CurrentSecurityContext(expression = "authentication.principal") User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
