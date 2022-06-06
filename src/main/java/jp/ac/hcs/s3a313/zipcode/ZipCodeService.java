package jp.ac.hcs.s3a313.zipcode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 郵便番号APIの業務ロジックを実現するクラス
 *
 * <p>
 * 処理が継続できなかった場合は、呼び出し元にスローせずに メソッド内で例外処理を行い、エラーメッセージを設定します <strong>
 * 
 * @author s20203053
 * 
 */
@Service
@Transactional
public class ZipCodeService {
	@Autowired
	private RestTemplate restTemplate;

	/** エンドポイント */
	private static final String URL = "https://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

	/**
	 * 郵便番号から検索結果形式に変換します。
	 *
	 * <p>
	 * 検索に失敗した場合はエラーメッセージのみ設定されます
	 *
	 * @param zipcode 検索番号の文字列(7桁)を格納(null不可)
	 * @return 郵便番号のレスポンス
	 **/
	public ZipCodeEntity execute(String zipcode) {
		// APIへリクエストを送信
		String json = request(zipcode);
		// インスタンスの生成
		ZipCodeEntity zipCodeEntity = new ZipCodeEntity();
		// 結果をデータに変換
		convert(json, zipCodeEntity);
		return zipCodeEntity;
	}

	private void convert(String json, ZipCodeEntity zipCodeEntity) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(json);
			// JSONノードの「status」を取得し、設定する
			String status = node.get("status").asText();
			zipCodeEntity.setStatus(status);
			// JSONノードの「message」を取得し、設定する
			String message = node.get("message").asText();
			zipCodeEntity.setMessage(message);

			// JSONノードの「result」の配列の数分繰り返す
			for (JsonNode result : node.get("results")) {
				ZipCodeData zipCodeData = new ZipCodeData();

				zipCodeData.setZipcode(result.get("zipcode").asText());
				zipCodeData.setPrefcode(result.get("prefcode").asText());
				zipCodeData.setAddress1(result.get("address1").asText());
				zipCodeData.setAddress2(result.get("address2").asText());
				zipCodeData.setAddress3(result.get("address3").asText());
				zipCodeData.setKana1(result.get("kana1").asText());
				zipCodeData.setKana2(result.get("kana2").asText());
				zipCodeData.setKana3(result.get("kana3").asText());
				// リストの末尾に追加
				zipCodeEntity.getList().add(zipCodeData);
			}
		} catch (IOException e) {
			// レスポンスがJSON形式でない場合
			zipCodeEntity.setErrorMessage("通信に失敗しました");
		}
	}

	private String request(String zipcode) {
		String json = restTemplate.getForObject(URL, String.class, zipcode);
		return json;
	}
}
