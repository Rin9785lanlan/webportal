package jp.ac.hcs.s3a313.main;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * サインアップに関する機能・画面を制御する
 */
@Controller
public class SignupController {
	
	private SignupService signupService;
	
		/**
		 * 新規登録画面を表示する
		 * @param model
		 * @return 新規登録画面
		 */
		@GetMapping("/signup")
		public String getLogin(SignupForm SignupForm ,Model model) {
			model.addAttribute("signupForm", SignupForm);
			return "signup";
		}
		
		@PostMapping("/signup/insert")
		public String addUser(@ModelAttribute @Validated SignupForm form, Principal principal,
			Model model) {
		int isSuceess = signupService.insertOne(form);
		if (isSuceess == 1) {
			model.addAttribute("message", "ユーザを登録しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ登録に失敗しました。操作をやり直してください");
		}
		return "login";
	}
}
