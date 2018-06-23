package mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.entities.User;
import mvc.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class LoginController {

	private UserRepository userRepository;
	
	@Autowired
	public LoginController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login(Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
			!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); -- to przekazuje wszysktie dane użytkownika
			return "redirect:/home";
		}
		model.addAttribute(new User());
		return "login";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute User user, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/login";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		userRepository.save(user);
		return "home";
	}
}
