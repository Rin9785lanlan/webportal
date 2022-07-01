package jp.ac.hcs.s3a313.signup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class SignupRepository {

	/** SQL １件追加 */
	private static final String SQL_INSERT_ONE = "INSERT INTO user_m (user_id, encrypted_password, user_name, role, enabled) VALUES(:userId, :encrypted_password, :user_name, :role, true)";

	/** 予測更新件数(ハードコーティング防止用） */
	private static final int EXPECTED_UPDATE_COUNT = 1;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザ情報を1件登録します
	 * <p>
	 * 更新件数が異常な場合は例外が発生します
	 * 
	 * @exception IncorrtionResultSizeDataAccessException 更新件数が異常な場合
	 * @param userData ユーザ情報(null不可)
	 * @ruturn 更新件数
	 */
	public int insert(SignupData signupData) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", signupData.getUserId());
		String password = passwordEncoder.encode(signupData.getPassword());
		params.put("encrypted_password", password);
		params.put("user_name", signupData.getUser_name());
		params.put("role", signupData.getRole());

		// ユーザ情報の追加処理を実行
		int result = jdbc.update(SQL_INSERT_ONE, params);

		if (result != EXPECTED_UPDATE_COUNT) {
			// 更新件数が異常な場合
			throw new IncorrectResultSizeDataAccessException("更新に失敗しました", EXPECTED_UPDATE_COUNT);
		}
		
		return result;
	}
}