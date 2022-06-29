package jp.ac.hcs.s3a313.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	/**
	 * TODOリスト画面を表示します。
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
		System.out.println(profileData);

		return "profile/Profile";
	}
	
}