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
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getUserCreate(UserForm userForm, Model model) {
		// 前回の入力内容を設定
		model.addAttribute("userForm", userForm);
		return "user/insert";
	}

	@PostMapping("/user/insert")
	public String createUser(@ModelAttribute @Validated UserForm form, BindingResult bindResult, Principal principal,
			Model model) {
		if (bindResult.hasErrors()) {
			return getUserCreate(form, model);
		}
		int isSuceess = userService.insertOne(form);
		if (isSuceess == 1) {
			model.addAttribute("message", "ユーザを登録しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ登録に失敗しました。操作をやり直してください");
		}
		return getUserList(model);
	}

	/**
	 * 【管理者】ユーザ詳細画面を表示します。
	 *
	 * @param updateUserForm ユーザ更新の入力値(null不可)
	 * @param userId         ユーザID(null不可)
	 * @param model          Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ詳細画面(null不可)
	 */
	@GetMapping("/user/detail")
	public String getUserDetail(UpdateUserForm updateUserForm, @RequestParam(name = "userId") String userId,
			Model model) {

		userService.getUser(userId, updateUserForm);
		model.addAttribute("UpdateUserForm", updateUserForm);
		return "user/detail";
	}

	/**
	 * 【管理者】ユーザ詳細画面を表示します。
	 *
	 * <p>
	 * 本機能は、ユーザ管理機能の更新機能を提供します。
	 * <p>
	 * <strong>この機能は管理者ロールのユーザのみが利用できます</strong>
	 *
	 * @param updateUserForm ユーザ更新の入力値(null不可)
	 * @param bindingResult  バリデーションの結果(null不可)
	 * @param principal      ログイン情報(null不可)
	 * @param model          Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ一覧画面(null不可)
	 */
	@PostMapping("/user/update")
	public String updateUser(@ModelAttribute @Validated UpdateUserForm updateUserForm, BindingResult bindingResult,
			Model model) {

		// 入力チェックでエラーの場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserDetail(updateUserForm, updateUserForm.getUserId(), model);
		}

		boolean isSuceess = userService.updateOne(updateUserForm);
		if (isSuceess) {
			model.addAttribute("message", "ユーザを更新しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ更新に失敗しました。操作をやり直してください。");
		}

		return getUserList(model);
	}

	/**
	 * 【管理者】ユーザ削除処理を実行します。
	 *
	 * <p>
	 * 本機能は、ユーザ管理機能の削除機能を提供します。
	 * <p>
	 * <strong>この機能は管理者ロールのユーザのみが利用できます</strong>
	 * 
	 * @param userId ユーザID(null不可)
	 * @param model  Viewに値を渡すオブジェクト(null不可)
	 * @return ユーザ一覧画面(null不可)
	 */
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam(name = "userId") String userId, Model model) {
		boolean isSuceess = userService.deleteOne(userId);
		if (isSuceess) {
			model.addAttribute("message", "ユーザを更新しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ更新に失敗しました。操作をやり直してください。");
		}

		return getUserList(model);
	}

	/**
	 * [管理者・上位のみ]ユーザ管理画面への遷移を実施します
	 * <p>
	 * 本機能は、ユーザ管理機能の一覧機能を提供します
	 * <p>
	 * <strong>この機能は管理者もしくは上位ロールのユーザのみが利用できます</strong>
	 * 
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 */
	@GetMapping("/user/delete")
	public String getDeleteUser(Model model) {

		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);

		return "user/delete";
	}

	/**
	 * 
	 * ここは自分で書く
	 * 
	 * @param users
	 * @param model
	 * @return
	 */
	@PostMapping("/user/delete/bulk")
	public String deleteBulkUser(@RequestParam(name = "users") String users, Model model) {
		boolean isSuceess = userService.deleteAll(users);
		if (isSuceess) {
			model.addAttribute("message", "選択したユーザを削除しました");
		} else {
			model.addAttribute("errorMessage", "ユーザ一括削除に失敗しました。操作をやり直してください。");
		}

		return getUserList(model);
	}
}
