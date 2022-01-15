package app.controller;

import app.model.Role;
import app.model.User;
import app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(defaultValue = "false") boolean checkbox_admin,
                             @RequestParam(defaultValue = "false") boolean checkbox_user, @RequestParam(defaultValue = "false") boolean checkbox_enabled) {
        Set<Role> roles = new HashSet<>();
        if (checkbox_admin) roles.add(appService.findByRole("ADMIN"));
        if (checkbox_user) roles.add(appService.findByRole("USER"));
        user.setEnabled(checkbox_enabled);
        user.setRoles(roles);
        System.out.println(user);
        return appService.saveUser(user) ? "redirect:/admin" : "edit";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", appService.findUser(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(defaultValue = "false") boolean checkbox_admin,
                             @RequestParam(defaultValue = "false") boolean checkbox_user, @RequestParam(defaultValue = "false") boolean checkbox_enabled) {
        Set<Role> roles = new HashSet<>();
        if (checkbox_admin) roles.add(appService.findByRole("ADMIN"));
        if (checkbox_user) roles.add(appService.findByRole("USER"));
        user.setId(id);
        user.setEnabled(checkbox_enabled);
        user.setRoles(roles);
        System.out.println(user);
        return appService.saveUser(user) ? "redirect:/admin" : "edit";
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
