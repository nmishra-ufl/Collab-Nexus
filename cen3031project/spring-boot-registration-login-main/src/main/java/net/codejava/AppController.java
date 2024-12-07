package net.codejava;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller to handle general application routes.
 * This includes the homepage, user registration, and user listing.
 */
@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	/**
	 * Displays the home page.
	 *
	 * @return The name of the home page template.
	 */
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	/**
	 * Displays the user registration form.
	 *
	 * @param model The model to bind the user object.
	 * @return The name of the signup form template.
	 */
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	/**
	 * Processes the user registration form.
	 * Encrypts the user's password and saves the user in the database.
	 *
	 * @param user The user submitted in the registration form.
	 * @return The name of the success page template.
	 */
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		return "register_success";
	}

	/**
	 * Displays a list of all registered users.
	 *
	 * @param model The model to bind the list of users.
	 * @return The name of the users list template.
	 */
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
}
