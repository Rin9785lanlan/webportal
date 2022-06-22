package jp.ac.hcs.s3a313.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * [管理者・上位のみ]ユーザ管理画面への遷移を実施します
	 * <p>本機能は、ユーザ管理機能の一覧機能を提供します
	 * <p><strong>この機能は管理者もしくは上位ロールのユーザのみが利用できます</strong>
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
	 * <p>本機能は、ユーザ追加機能の一覧機能を提供します
	 * <p><strong>この機能は管理者もしくは上位ロールのユーザのみが利用できます</strong>
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ管理画面へのパス(null不可)
	 */
	@GetMapping("/user/insert")
	public String getUserCreate(Model model) {

		// 前回の入力内容を設定
		model.addAttribute("userForm", userForm);

		return "user/insert";
	}
	
	
}
