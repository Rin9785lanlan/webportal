package jp.ac.hcs.s3a313.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * グルメ検索機能の業務ロジックを処理します。
 * 
 * <p>処理が継続できなくなった場合は、呼び出し元にスローせずに
 * メソッド内で例外処理を行い、エラーメッセージを設定します。
 * <strong>呼び出し元にnullが返却されることはありません。</strong>
 * 
 * @author 情報太郎
 */
@Transactional
@Service
public class GourmetService {

	/** リクルート APIキー ※取扱注意 */
	private static final String API_KEY = "0b7e685bff386cca";

	/** グルメサーチAPI リクエストURL */
	private static final String URL =
			"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * APIから情報を取得する処理
	 * <p>受け取った情報をもとにAPIにリクエストを送信します
	 * 
	 * @param shopname, large_service_area
	 * @return shopEntity
	 * */
	public ShopEntity execute(String shopname, String large_service_area) {

		String json = request(shopname, large_service_area);

		ShopEntity shopEntity = new ShopEntity();
		
		convert(shopname, json, shopEntity);

		return shopEntity;
	}
	
	/**
	 * jsonデータを加工する処理
	 * 
	 * */
	private void convert(String shopname, String json, ShopEntity shopEntity) {
		
		shopEntity.setSearchWord(shopname);

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);

			for (JsonNode shop : node.get("results").get("shop")) {

				ShopData shopData = extracted(shop);
				shopEntity.getShops().add(shopData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ShopData extracted(JsonNode shop) {
		ShopData shopData = new ShopData();
		shopData.setShopname(shop.get("name").asText());
		shopData.setImage(shop.get("logo_image").asText());
		shopData.setUrl(shop.get("urls").get("pc").asText());
		
		return shopData;
	}

	/**
	 * APIにリクエストを送信するし、レスポンスを受け取る処理
	 * 
	 * @return json
	 * */
	private String request(String shopname, String large_service_area) {
		// 外部APIアクセス
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		return json;
	}

}