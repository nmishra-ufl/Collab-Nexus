package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Controller for managing group-related operations.
 * Handles group creation, browsing, joining, leaving, and displaying user-specific groups.
 */
@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Displays the group creation form.
     *
     * @param model The model to bind a new group object.
     * @return The name of the group creation template.
     */
    @GetMapping("/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "create_group";
    }

    /**
     * Processes the group creation form.
     *
     * @param group The group submitted in the form.
     * @param principal The currently logged-in user.
     * @return Redirects to the browse groups page.
     */
    @PostMapping("/create")
    public String createGroup(@ModelAttribute Group group, Principal principal) {
        groupRepo.save(group);
        return "redirect:/groups/browse";
    }

    /**
     * Displays all groups available for browsing.
     *
     * @param model The model to bind the list of groups.
     * @return The name of the browse groups template.
     */
    @GetMapping("/browse")
    public String browseGroups(Model model) {
        List<Group> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "browse_groups";
    }

    /**
     * Allows the user to join a group.
     *
     * @param groupId The ID of the group to join.
     * @param principal The currently logged-in user.
     * @return Redirects to the browse groups page.
     */
    @PostMapping("/join/{groupId}")
    public String joinGroup(@PathVariable Long groupId, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        Group group = groupRepo.findById(groupId).orElse(null);
        if (group != null && user != null) {
            group.getUsers().add(user);
            user.getGroups().add(group);
            groupRepo.save(group);
            userRepo.save(user);
        }
        return "redirect:/groups/browse";
    }

    /**
     * Displays groups that the current user is part of.
     *
     * @param model The model to bind the user's groups.
     * @param principal The currently logged-in user.
     * @return The name of the user's groups template.
     */
    @GetMapping("/my-groups")
    public String viewMyGroups(Model model, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);

        if (currentUser != null) {
            Set<Group> myGroups = currentUser.getGroups();
            model.addAttribute("myGroups", myGroups);
        } else {
            model.addAttribute("myGroups", null);
        }

        return "my_groups";
    }

    /**
     * Allows the user to leave a group they are part of.
     *
     * @param groupId The ID of the group to leave.
     * @param principal The currently logged-in user.
     * @return Redirects to the user's groups page.
     */
    @PostMapping("/leave/{groupId}")
    public String leaveGroup(@PathVariable Long groupId, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);

        if (currentUser != null) {
            Group group = groupRepo.findById(groupId).orElse(null);

            if (group != null) {
                group.getUsers().remove(currentUser);
                currentUser.getGroups().remove(group);
                groupRepo.save(group);
                userRepo.save(currentUser);
            }
        }
        return "redirect:/groups/my-groups";
    }
}
