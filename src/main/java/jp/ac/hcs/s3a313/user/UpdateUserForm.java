package jp.ac.hcs.s3a313.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UpdateUserForm {
	/** ユーザID （メールアドレス） */
	@NotBlank(message = "{require_chaek}")
	@Email(message = "{email_cheak}")
	private String userId;

	/** 名前 */
	@NotBlank(message = "{require_chaek}")
	@Length(min = 2, max = 50, message = "{length_check}")
	private String username;

	/** パスワード */
	//TODO
	private String password;
	
	/** 権限 */
	@Pattern(regexp="^(ROLE_ADMIN|ROLE_TOP|ROLE_GENERAL)$")
	private String role;
	
	/** 有効性*/
	@Pattern(regexp="^(true|false)$")
	private String enabled;
}
