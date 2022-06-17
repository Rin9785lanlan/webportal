package jp.ac.hcs.s3a313.gourmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

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
	private static final String API_KEY = "2613d3277469ede5";

	/** グルメサーチAPI リクエストURL */
	private static final String URL =
			"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";

	@Autowired
	private RestTemplate restTemplate;
	
	public ShopEntity execute(String shopname, String large_service_area) {

		ShopEntity shopEntity = new ShopEntity();

		// TODO

		return shopEntity;
	}

	private void convert(String shopname, String json, ShopEntity shopEntity) {
		
		shopEntity.setSearchWord(shopname);

		// TODO
	}

	private ShopData extracted(JsonNode shop) {
		ShopData shopData = new ShopData();
		
		// TODO
		
		return shopData;
	}

	private String request(String shopname, String large_service_area) {
		// 外部APIアクセス
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		return json;
	}

}