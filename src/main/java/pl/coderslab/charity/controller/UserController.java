package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-user")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    /**
     * Info about currently logged-in user, but with no relation to database e.g.:
     * You are logged as org.springframework.security.core.userdetails.User [Username=admin@admin.pl,
     * Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true,
     * AccountNonLocked=true, Granted Authorities=[ROLE_USER]]
     */
    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        return "You are logged as " + customUser;
    }

    @GetMapping("/admin2")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        return "Hello " + customUser.getUser().getEmail() + " / " + customUser.getUsername();
    }
}
