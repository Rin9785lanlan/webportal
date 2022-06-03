package jp.ac.hcs.s3a313.zipcode;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ZipCodeController {
	/**
	 * 郵便番号から住所を検索し、結果画面を呼び出します
	 * 
	 * <p>本機能は、郵便番号検索APIを内部で呼び出して結果を表示します<br>
	 * 使用については、下記のドキュメントを参照してください。
	 * <p>https://zipcloud.ibsnet.co.jp/doc/api
	 * 
	 *@param zipcode 郵便番号の文字列（7桁）を格納(null不可)
	 *@param principal ログイン中のユーザ情報を格納(null不可)
	 *@param　model Viewに値を渡すオブジェクト(null不可)
	 *@return 郵便番号結果へのパス(null不可)
	 * */
	@PostMapping("/zip")
	public String getZipCode(@RequestParam("zipcode") String zipcode,
			Principal principal, Model model) {
		return "zipcode/result";
	}
}
