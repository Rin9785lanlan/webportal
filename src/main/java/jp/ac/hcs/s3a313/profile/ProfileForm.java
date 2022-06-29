package jp.ac.hcs.s3a313.profile;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ProfileForm {
	/* ユーザID */
	private String user_id;
	
	/* ユーザ名 */
	private String user_name;
	
	/* 取得した資格 */
	private String qualification;
	
	
	/* 表示名（ニックネーム） */
	@Length(max = 50, message = "{length_check}")
	private String nickname;
	
	/* 自己紹介文 */
	@Length(max = 100, message = "{length_check}")
	private String comment;
}