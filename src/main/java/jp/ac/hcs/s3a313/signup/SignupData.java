package jp.ac.hcs.s3a313.signup;

import lombok.Data;

@Data
public class SignupData {

	/**
	 * ユーザID（メールアドレス）
	 * 主キー、必須入力、メールアドレス形式
	 */
	private String userId;

	/**
	 * パスワード
	 * 必須入力、長さ4から100桁まで、半角英数字のみ
	 */
	private String password;

	/**
	 * アカウント有効性
	 * - 有効 : true
	 * - 無効 : false
	 * ユーザ作成時はtrue固定
	 */
	private boolean enabled;

	/**
	 * ユーザ名
	 * 必須入力
	 */
	private String user_name;

	/**
	 * 権限
	 * - 管理 : "ROLE_ADMIN"
	 * - 上位： "ROLE_TOP"
	 * - 一般 : "ROLE_GENERAL"
	 * 必須入力
	 */
	private String role;

}

