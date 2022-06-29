package jp.ac.hcs.s3a313.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * サインアップに関する機能・画面を制御する
 */
@Controller
public class SignupController {
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
}
