package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.StripeService;
import com.example.nagoyameshi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	private final UserRepository userRepository;
	private final UserService userService;
	
	
	public SubscriptionController (UserRepository userRepository,UserService userService){
		this.userRepository = userRepository;
		this.userService = userService;
		
	}
	@Autowired
	private StripeService stripeService;
		
		@GetMapping("/register")
		public String register (@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
			User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
			return "/subscription/register";
			
		
	}

		@PostMapping("/create")
		public String create(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes,Model model,HttpServletRequest httpServletRequest) {
			User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
			Integer amount = 300;
			UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getFurigana(), user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail(),amount);
			String sessionId = stripeService.createStripeSession(user.getName(), userEditForm, httpServletRequest);
			model.addAttribute("sessionId", sessionId);
			model.addAttribute("userEditForm", userEditForm);
			userService.updateRole(user,"ROLE_PREMIUM");
			userService.updateAmount(userEditForm);
			userService.refreshAuthenticationByRole("ROLE_PREMIUM");
			redirectAttributes.addFlashAttribute("successMessage", "有料会員の登録が完了しました。");
			return "redirect:/";
		}
		
		
		@GetMapping("/cancel")
		public String cancel() {
			return "subscription/cancel";
		}
		
		@PostMapping("/delete")
		public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
			User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
			
		
			
				userService.updateRole(user, "ROLE_GENERAL");
				userService.refreshAuthenticationByRole("ROLE_GENERAL");
				redirectAttributes.addFlashAttribute("successMessage", "有料プランを解約しました。");
			
			
			return "redirect:/";
		}
		
		
		

}
