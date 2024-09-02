package programmers.baemin.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import programmers.baemin.user.domain.UserLoginDto;
import programmers.baemin.user.domain.UserRegisterDto;
import programmers.baemin.user.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/user/login")
	public String loginForm() {
		return "user/login-form";
	}

	@PostMapping("/user/login")
	public String login(@ModelAttribute UserLoginDto userLoginDto, HttpSession session,
		RedirectAttributes redirectAttributes) {
		log.info("Login Request");
		log.info("userLoginDto : {}", userLoginDto);
		if (userService.login(userLoginDto)) {
			session.setAttribute("login", userLoginDto.getLoginId());
			if (userLoginDto.getRedirectUrl() != null) {
				return "redirect:" + userLoginDto.getRedirectUrl();
			}
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("error", "잘못된 아이디 또는 패스워드입니다.");
			return "redirect:/user/login";
		}
	}

	@GetMapping("/user/register")
	public String registerForm() {
		return "user/register-form";
	}

	@PostMapping("/user/register")
	public String register(@ModelAttribute UserRegisterDto userRegisterDto) {
		userService.register(userRegisterDto);
		return "redirect:/main";
	}

	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
