package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private UserRepository userRepo;

    // Show group creation form
    @GetMapping("/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "create_group";
    }

    // Handle group creation
    @PostMapping("/create")
    public String createGroup(@ModelAttribute Group group, Principal principal) {
        groupRepo.save(group);
        return "redirect:/groups/browse";
    }

    // Show all groups
    @GetMapping("/browse")
    public String browseGroups(Model model) {
        List<Group> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "browse_groups";
    }

    // Join a group
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

    @GetMapping("/my-groups")
    public String viewMyGroups(Model model, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);

        if (currentUser != null) {
            // Fetch groups the user is part of
            Set<Group> myGroups = currentUser.getGroups();
            model.addAttribute("myGroups", myGroups);
        } else {
            model.addAttribute("myGroups", null);
        }

        return "my_groups";
    }

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
