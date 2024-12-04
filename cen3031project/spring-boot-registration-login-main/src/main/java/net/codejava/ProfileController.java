package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    // Display the profile creation/edit form
    @GetMapping("/profile")
    public String showProfileForm(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "profile_form";
    }

    // Save the profile details and redirect to the dashboard
    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute User user, Principal principal) {
        String email = principal.getName();
        User existingUser = userRepo.findByEmail(email);

        if (existingUser != null) {
            existingUser.setSkills(user.getSkills());
            existingUser.setExperience(user.getExperience());
            existingUser.setAvailability(user.getAvailability());
            existingUser.setBio(user.getBio());
            userRepo.save(existingUser);
        }

        // Redirect to the dashboard (list of users)
        return "redirect:/users";
    }

    // Display other users' profiles
    @GetMapping("/user/profile/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User user = userRepo.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user_profile";
    }
}
