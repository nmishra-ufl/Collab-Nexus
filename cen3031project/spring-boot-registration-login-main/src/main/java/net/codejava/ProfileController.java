package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

/**
 * Controller to handle user profile-related operations.
 * Includes profile creation, editing, and viewing other user profiles.
 */
@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    /**
     * Displays the profile creation/edit form for the logged-in user.
     *
     * @param model The model to bind the user object.
     * @param principal The currently logged-in user's details.
     * @return The name of the profile form template.
     */
    @GetMapping("/profile")
    public String showProfileForm(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "profile_form";
    }

    /**
     * Saves the profile details of the logged-in user and redirects to the dashboard.
     *
     * @param user The updated user object from the form.
     * @param principal The currently logged-in user's details.
     * @return Redirects to the user dashboard.
     */
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

        return "redirect:/users";
    }

    /**
     * Displays the profile of another user.
     *
     * @param id The ID of the user whose profile is to be viewed.
     * @param model The model to bind the user's details.
     * @return The name of the user profile template.
     */
    @GetMapping("/user/profile/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User user = userRepo.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user_profile";
    }
}
