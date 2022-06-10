package jp.ac.hcs.s3a313.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 天気予報機能を表す。
 *
 * <p>本機能は、天気予報を検索して当日・明日・明後日
 * に渡る3日間の天気予報を表示します。
 *
 * <p>利用には、<code>getWeather</code>をご覧ください。
 * @author s20203053
 *
 */
@Controller
public class WeatherController {
	@Autowired
	private WeatherService weatherService;

	/**
	 * 都市コードをもとに該当地域の天気予報を検索し、結果画面を表示します。
	 *
	 * <p>本機能は、天気予報 API（livedoor 天気互換）を内部で呼び出して結果を表示します。
	 * 仕様については、下記のドキュメントを参照してください。
	 * <p>https://weather.tsukumijima.net/
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return 天気予報検索結果画面へのパス(null不可)
	 */
	@PostMapping("/weather")
	public String getWeather(@RequestParam("citycode") String citycode,
			Principal principal, Model model) {

		// 結果を取得
		WeatherEntity weatherEntity = weatherService.execute(citycode);
		// 結果を画面に設定
		model.addAttribute("weatherEntity", weatherEntity);

		return "weather/result";
	}
}
