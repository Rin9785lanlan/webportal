package jp.ac.hcs.s3a313.zipcode;

import lombok.Data;

/**
 * 1見分の郵便番号情報を管理する
 * 
 * <p>レスポンスフィールドのresult内のデータを管理します。
 * @author s20203053
 * */
@Data
public class ZipCodeData {
	/**郵便番号*/
	private String zipcode;
	
	/**都道府県コード*/
	private String prefcode;
	
	/**都道府県*/
	private String address1;
	
	/**市区町村名*/
	private String address2;
	
	/**町域名*/
	private String address3;
	
	/**都道府県名カナ*/
	private String kana1;
	
	/**市区町村名カナ*/
	private String kana2;
	
	/**町域名カナ*/
	private String kana3;
}
