package jp.ac.hcs.s3a313.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
	 * グルメ検索機能を表す。
	 * 
	 * <p>本機能は、グルメ情報を検索して各種情報を画面に表示します。
	 * <p>利用には、<code>postGourmet</code>をご覧ください。
	 * 
	 * @author 情報太郎
	 *
	 */
	@Controller
	public class GourmetController {

		@Autowired
		private GourmetService gourmetService;

		/**
		 * キーワードをもとに飲食店情報を検索し、結果画面を表示します。
		 * 
		 * <p>本機能は、リクルート社が提供するAPIを内部で呼び出して結果を表示します。
		 * 仕様については、下記のドキュメントを参照してください。
		 * <p>https://webservice.recruit.co.jp/doc/hotpepper/reference.html
		 * 
		 * @param shopname キーワード文字列を格納(null不可)
		 * @param areaCode 都市コードの文字列を格納(null不可)
		 * @param principal ログイン中のユーザ情報を格納(null不可)
		 * @param model Viewに値を渡すオブジェクト(null不可)
		 * @return 天気予報検索結果画面へのパス(null不可)
		 */
		@GetMapping("/gourmet")
		public String postGourmet(@RequestParam("shopname") String shopname,
								@RequestParam("areaCode") String areaCode,
				Principal principal, Model model) {
			// 結果を取得
			ShopEntity shopEntity = gourmetService.execute(shopname,areaCode);
			// 結果を画面に設定
			model.addAttribute("shopEntity", shopEntity);
			
			return "gourmet/list";
		}
	}