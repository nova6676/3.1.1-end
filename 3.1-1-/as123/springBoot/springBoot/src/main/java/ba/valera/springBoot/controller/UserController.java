package ba.valera.springBoot.controller;


import ba.valera.springBoot.model.User;
import ba.valera.springBoot.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("showAllUsers", userService.getAllUsers());
        return "showAllUsers";
    }

    @GetMapping("/{id}")
    public String getUserById(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("showAllUsers", userService.findAll());
        return "showAllUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @RequestParam("id") long id) {
        if (bindingResult.hasErrors()){
            return "edit";
        }
        userService.update(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

}
