package app.service;

import app.model.Role;
import app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AppService extends UserDetailsService {

    List<User> findAllUsers();

    User findUser(Long userId) throws NullPointerException;

    void deleteUser(Long userId);

    List<Role> findAllRoles();

    boolean saveUser(User user, BindingResult bindingResult, Model model);
}
