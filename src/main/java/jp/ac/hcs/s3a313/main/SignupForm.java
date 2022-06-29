package jp.ac.hcs.s3a313.main;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * ユーザ作成画面の入力値を保持するクラスです
 * <p>
 * 各項目のデータ使用は基本設計書を参照してください
 * 
 * @author s20203053
 *
 */
@Data
public class SignupForm {
	/** ユーザID （メールアドレス） */
	@NotBlank(message = "{require_chaek}")
	@Email(message = "{email_cheak}")
	private String userId;

	/** パスワード */
	@NotBlank(message = "{require_chaek}")
	@Length(min = 6, max = 100, message = "{length_check}")
	private String password;

	/** 名前 */
	@NotBlank(message = "{require_chaek}")
	@Length(min = 2, max = 50, message = "{length_check}")
	private String username;

}
