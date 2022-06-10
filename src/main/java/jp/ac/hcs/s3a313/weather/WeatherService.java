
package jp.ac.hcs.s3a313.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 天気予報の業務ロジックを実現するクラスです。
 *
 * <p>
 * 処理が継続できなくなった場合は、呼び出し元にスローせずに メソッド内で例外処理を行い、エラーメッセージを設定します。
 * <strong>呼び出し元にnullが返却されることはありません。</strong>
 *
 * @author 情報太郎
 *
 */
@Transactional
@Service
public class WeatherService {

	@Autowired
	RestTemplate restTemplate;

	/** エンドポイント */
	private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";

	private static final String SAPPORO = "016010";

	/**
	 * 都市コードから検索結果形式に変換します。
	 *
	 * <p>
	 * 検索に失敗した場合は、エラーメッセージのみ設定されます。
	 *
	 * @return 天気予報結果のレスポンス
	 */
	public WeatherEntity execute(String cityCode) {
		// レスポンス(json)を取得
		String json = request(cityCode);
		// 結果(箱)を生成
		WeatherEntity weatherEntity = new WeatherEntity();
		// レスポンスを結果(箱)に変換
		convert(json, weatherEntity);

		// 地域名を設定
		weatherEntity.toLocationName(cityCode);

		return weatherEntity;
	}

	private void convert(String json, WeatherEntity weatherEntity) {

		// 変換ライブラリを生成
		ObjectMapper mapper = new ObjectMapper();

		try {
			// レスポンス(json)を構造体へ変換
			JsonNode node = mapper.readTree(json);
			
			//天気予報概要
			weatherEntity.setDescription(node.get("description").get("text").asText());
			
			// 「forecasts」ノード(配列)を取得し繰り返す
			for (JsonNode forecast : node.get("forecasts")) {
				WeatherData data = new WeatherData();
				data.setDateLabel(forecast.get("dateLabel").asText());
				data.setTelop(forecast.get("telop").asText());
				data.setImage(forecast.get("image").get("url").asText());

				// 配列の末尾に追加
				weatherEntity.getForecasts().add(data);
			}
			
		} catch (IOException e) {
			weatherEntity.setErrorMessage("通信に失敗しました");
		}
	}

	private String request(String cityCode) {
		String json = restTemplate.getForObject(URL, String.class, cityCode);
		return json;
	}
}
