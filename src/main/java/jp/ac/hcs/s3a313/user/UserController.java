package jp.ac.hcs.s3a313.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * [管理者・上位のみ]ユーザ管理画面への遷移を実施します
	 * <p>
	 * 本機能は、ユーザ管理機能の一覧機能を提供します
	 * <p>
	 * <strong>この機能は管理者もしくは上位ロールのユーザのみが利用できます</strong>
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ管理画面へのパス(null不可)
	 */
	@GetMapping("/user/list")
	public String getUserList(Model model) {

		// 結果を取得
		UserEntity userEntity = userService.selectAll();
		// 結果を画面に設定
		model.addAttribute("userEntity", userEntity);

		return "user/list";
	}

	/**
	 * [管理者・上位のみ]ユーザ追加画面を表示します
	 * <p>
	 * 本機能は、ユーザ追加機能の一覧機能を提供します
	 * <p>
	 * <strong>この機能は管理者もしくは上位ロールのユーザのみが利用できます</strong>
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ管理画面へのパス(null不可)
	 */
	@GetMapping("/user/insert")
	public String getUserCreate(UserForm userForm,Model model) {
		//前回の入力内容を設定
		model.addAttribute("userForm",userForm);
		return "user/insert";
	}

	@PostMapping("/user/insert")
	public String createUser(@ModelAttribute @Validated UserForm form, BindingResult bindResult, Principal principal,
			Model model) {
		if (bindResult.hasErrors()) {
			return getUserCreate(form, model);
		}
		boolean isSuceess = userService.insertOne(form);
		if (isSuceess) {
			model.addAttribute("message", "ユーザを登録しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ登録に失敗しました。操作をやり直してください");
		}
		return getUserList(model);
	}
}
