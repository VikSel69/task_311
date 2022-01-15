package app.controller;

import app.model.Role;
import app.model.User;
import app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppService appService;

    @Autowired
    public AdminController(AppService appService) {
        this.appService = appService;
    }


    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", appService.findUser(id));
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        return appService.saveUser(user) ? "redirect:/admin" : "user-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long userId) {
        appService.deleteUser(userId);
        return "redirect:/admin";
    }

    @ModelAttribute("allRoles")
    public List<Role> initializeRoles() {
        return appService.findAllRoles();
    }

}
