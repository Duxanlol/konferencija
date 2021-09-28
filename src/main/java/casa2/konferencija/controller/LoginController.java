package casa2.konferencija.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import casa2.konferencija.entity.Uloga;
import casa2.konferencija.repository.OsobaRepository;
import casa2.konferencija.repository.UlogaRepository;

@Controller
@ControllerAdvice
@RequestMapping(value = "auth")
public class LoginController {

	@Autowired
	UlogaRepository ulogaRepository;

	@Autowired
	OsobaRepository osobaRepository;

	@ModelAttribute
	public void getRoles(Model model) {
		List<Uloga> roles = ulogaRepository.findAll();
		model.addAttribute("roles", roles);
	}

	@RequestMapping(value = "/perform_login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginHello() {
		return "login";
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String deniedPage() {
		return "access_denied";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/auth/login";
	}

}
