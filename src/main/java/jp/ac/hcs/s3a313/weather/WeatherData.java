package jp.ac.hcs.s3a313.weather;

import lombok.Data;

/**
 * 1件分の天気情報です。
 * 
 * <p>レスポンスフィールドのforecasts内のデータを管理します。
 * @author 情報太郎
 *
 */
@Data
public class WeatherData {

	/** 予報日 */
	private String dateLabel;

	/** 天気 */
	private String telop;

	/** 天気アイコン */
	private String image;

}