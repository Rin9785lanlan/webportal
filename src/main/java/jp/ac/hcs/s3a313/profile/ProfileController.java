package jp.ac.hcs.s3a313.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	/**
	 * プロフィール画面を表示します。
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return タスク管理画面へのパス(null不可)
	 */
	@GetMapping("/change")
	public String getProfile(@AuthenticationPrincipal UserDetails user, Model model) {
		// 結果を取得
		ProfileData profileData = profileService.selectAll(user.getUsername());
		// 結果を画面に設定
		model.addAttribute("userProfile", profileData);

		return "profile/Profile";
	}
	
	/**
	 * プロフィール画面を表示します。
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return タスク管理画面へのパス(null不可)
	 */
	public String getProfile(String userId, Model model) {
		// 結果を取得
		ProfileData profileData = profileService.selectAll(userId);
		// 結果を画面に設定
		model.addAttribute("userProfile", profileData);

		return "profile/Profile";
	}
	
	/**
	 * プロフィール画面を編集します。
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return タスク管理画面へのパス(null不可)
	 */
	@PostMapping("/profile")
	public String editProfile(@ModelAttribute @Validated ProfileForm userProfile,Model model) {
		// 結果を取得
		profileService.updateOne(userProfile);
		return getProfile(userProfile.getUser_id(),model);
	}
}
