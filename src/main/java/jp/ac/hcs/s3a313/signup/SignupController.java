package jp.ac.hcs.s3a313.signup;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 * 
	 * @param model
	 * @return 新規登録画面
	 */
	@GetMapping("/signup")
	public String getSign(SignupForm SignupForm, Model model) {
		model.addAttribute("signupForm", SignupForm);
		return "signup";
	}

	@PostMapping("/signup/insert")
	public String addUser(@ModelAttribute @Validated SignupForm form, BindingResult bindResult, Principal principal,
			Model model) {
		String return_url = "signup";
		if (bindResult.hasErrors()) {
			model.addAttribute("errorMessage", "ユーザ登録に失敗しました。操作をやり直してください");
		}
		
		int isSuceess = 0;
		if (isSuceess == 0) {
			model.addAttribute("errorMessage", "ユーザ登録に失敗しました。操作をやり直してください");
		}
		return return_url;
	}
}
