package jp.ac.hcs.s3a313.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShopEntity {
	/** 検索名 */
	private String searchWord;

	/** 店舗詳細 */
	private List<ShopData> shops = new ArrayList<ShopData>();

	/** エラーメッセージ(表示用) */
	private String errorMessage;
}
