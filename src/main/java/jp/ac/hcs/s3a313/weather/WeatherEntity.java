package jp.ac.hcs.s3a313.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 天気予報検索のレスポンスです。
 * 
 * <p>
 * 各項目のデータ仕様については、APIの仕様を参照してください。 予報日は今日/明日/明後日の３つが配列で取得できるため、リスト構造となっている。
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
		case "016010":
			name = "札幌";
			break;
		case "016020":
			name = "岩見沢";
			break;
		case "016030":
			name = "倶知安";
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
		case "017010":
			name = "函館";
			break;
		case "017020":
			name = "江差";
			break;
		case "020010":
			name = "青森";
			break;
		case "020020":
			name = "むつ";
			break;
		case "020030":
			name = "八戸";
			break;
		case "030010":
			name = "盛岡";
			break;
		case "030020":
			name = "宮古";
			break;
		case "030030":
			name = "大船渡";
			break;
		case "040010":
			name = "仙台";
			break;
		case "040020":
			name = "白石";
			break;
		case "050010":
			name = "秋田";
			break;
		case "050020":
			name = "横手";
			break;
		case "060010":
			name = "山形";
			break;
		case "060020":
			name = "米沢";
			break;
		case "060030":
			name = "酒田";
			break;
		case "060040":
			name = "新庄";
			break;
		case "070010":
			name = "福島";
			break;
		case "070020":
			name = "小名浜";
			break;
		case "070030":
			name = "若松";
			break;
		case "130010":
			name = "東京";
			break;
		case "130020":
			name = "大島";
			break;
		case "130030":
			name = "八丈島";
			break;
		case "130040":
			name = "父島";
			break;
		case "140010":
			name = "横浜";
			break;
		case "140020":
			name = "小田原";
			break;
		case "110010":
			name = "さいたま";
			break;
		case "110020":
			name = "熊谷";
			break;
		case "110030":
			name = "秩父";
			break;
		case "120010":
			name = "千葉";
			break;
		case "120020":
			name = "銚子";
			break;
		case "120030":
			name = "館山";
			break;
		case "080010":
			name = "水戸";
			break;
		case "080020":
			name = "土浦";
			break;
		case "090010":
			name = "宇都宮";
			break;
		case "090020":
			name = "大田原";
			break;
		case "100010":
			name = "前橋";
			break;
		case "100020":
			name = "みなかみ";
			break;
		case "190010":
			name = "甲府";
			break;
		case "190020":
			name = "河口湖";
			break;
		case "150010":
			name = "新潟";
			break;
		case "150020":
			name = "長岡";
			break;
		case "150030":
			name = "高田";
			break;
		case "150040":
			name = "相川";
			break;
		case "200010":
			name = "長野";
			break;
		case "200020":
			name = "松本";
			break;
		case "200030":
			name = "飯田";
			break;
		case "160010":
			name = "富山";
			break;
		case "160020":
			name = "伏木";
			break;
		case "170010":
			name = "金沢";
			break;
		case "170020":
			name = "輪島";
			break;
		case "180010":
			name = "福井";
			break;
		case "180020":
			name = "敦賀";
			break;
		case "230010":
			name = "名古屋";
			break;
		case "230020":
			name = "豊橋";
			break;
		case "210010":
			name = "岐阜";
			break;
		case "210020":
			name = "高山";
			break;
		case "220010":
			name = "静岡";
			break;
		case "220020":
			name = "網代";
			break;
		case "220030":
			name = "三島";
			break;
		case "220040":
			name = "浜松";
			break;
		case "240010":
			name = "津";
			break;
		case "240020":
			name = "尾鷲";
			break;
		case "270000":
			name = "大阪";
			break;
		case "280010":
			name = "神戸";
			break;
		case "280020":
			name = "豊岡";
			break;
		case "260010":
			name = "京都";
			break;
		case "260020":
			name = "舞鶴";
			break;
		case "250010":
			name = "大津";
			break;
		case "250020":
			name = "彦根";
			break;
		case "290010":
			name = "奈良";
			break;
		case "290020":
			name = "風屋";
			break;
		case "300010":
			name = "和歌山";
			break;
		case "300020":
			name = "潮岬";
			break;
		case "310010":
			name = "鳥取";
			break;
		case "310020":
			name = "米子";
			break;
		case "320010":
			name = "松江";
			break;
		case "320020":
			name = "浜田";
			break;
		case "320030":
			name = "西郷";
			break;
		case "330010":
			name = "岡山";
			break;
		case "330020":
			name = "津山";
			break;
		case "340010":
			name = "広島";
			break;
		case "340020":
			name = "庄原";
			break;
		case "350010":
			name = "下関";
			break;
		case "350020":
			name = "山口";
			break;
		case "350030":
			name = "柳井";
			break;
		case "350040":
			name = "萩";
			break;
		case "360010":
			name = "徳島";
			break;
		case "360020":
			name = "日和佐";
			break;
		case "370000":
			name = "高松";
			break;
		case "380010":
			name = "松山";
			break;
		case "380020":
			name = "新居浜";
			break;
		case "380030":
			name = "宇和島";
			break;
		case "390010":
			name = "高知";
			break;
		case "390020":
			name = "室戸岬";
			break;
		case "390030":
			name = "清水";
			break;
		case "400010":
			name = "福岡";
			break;
		case "400020":
			name = "八幡";
			break;
		case "400030":
			name = "飯塚";
			break;
		case "400040":
			name = "久留米";
			break;
		case "440010":
			name = "大分";
			break;
		case "440020":
			name = "中津";
			break;
		case "440030":
			name = "日田";
			break;
		case "440040":
			name = "佐伯";
			break;
		case "420010":
			name = "長崎";
			break;
		case "420020":
			name = "佐世保";
			break;
		case "420030":
			name = "厳原";
			break;
		case "420040":
			name = "福江";
			break;
		case "410010":
			name = "佐賀";
			break;
		case "410020":
			name = "伊万里";
			break;
		case "430010":
			name = "熊本";
			break;
		case "430020":
			name = "阿蘇乙姫";
			break;
		case "430030":
			name = "牛深";
			break;
		case "430040":
			name = "人吉";
			break;
		case "450010":
			name = "宮崎";
			break;
		case "450020":
			name = "延岡";
			break;
		case "450030":
			name = "都城";
			break;
		case "450040":
			name = "高千穂";
			break;
		case "460010":
			name = "鹿児島";
			break;
		case "460020":
			name = "鹿屋";
			break;
		case "460030":
			name = "種子島";
			break;
		case "460040":
			name = "名瀬";
			break;
		case "471010":
			name = "那覇";
			break;
		case "471020":
			name = "名護";
			break;
		case "471030":
			name = "久米島";
			break;
		case "472000":
			name = "南大東";
			break;
		case "473000":
			name = "宮古島";
			break;
		case "474010":
			name = "石垣島";
			break;
		case "474020":
			name = "与那国島";
			break;
		default:
			name = "江差";
		}
		setLocationName(name);
	}
}
