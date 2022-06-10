package jp.ac.hcs.s3a313.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 天気予報検索のレスポンスです。
 * 
 * <p>各項目のデータ仕様については、APIの仕様を参照してください。
 * 予報日は今日/明日/明後日の３つが配列で取得できるため、リスト構造となっている。
 * 
 * @author 情報太郎
 *
 */
@Data
public class WeatherEntity {

	/** タイトル */
	private String title;

	/** 説明文 */
	private String description;

	/** 天気情報のリスト */
	private List<WeatherData> forecasts = new ArrayList<WeatherData>();

	/** エラーメッセージ(表示用) */
	private String errorMessage;
	
	/** 地名(表示のみ) */
	private String locationName;

	public void toLocationName(String cityCode) {
		String name = "";
		switch (cityCode) {
		case "011000":
			name = "稚内";
			break;
		case "012010":
			name = "旭川";
			break;
		case "012020":
			name = "留萌";
			break;
		case "013010":
			name = "網走";
			break;
		case "013020":
			name = "北見";
			break;
		case "013030":
			name = "紋別";
			break;
		case "014010":
			name = "根室";
			break;
		case "014020":
			name = "釧路";
			break;
		case "014030":
			name = "帯広";
			break;
		case "015010":
			name = "室蘭";
			break;
		case "015020":
			name = "浦河";
			break;
		case "016010":
			name = "札幌";
			break;
		case "016020":
			name = "岩見沢";
			break;
		case "016030":
			name = "倶知安";
			break;
		case "017010":
			name = "函館";
			break;
		default:
			name = "江差";
		}
		setLocationName(name);
	}
}
