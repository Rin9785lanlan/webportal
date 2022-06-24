package jp.ac.hcs.s3a313.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * ユーザ管理に関わるDBアクセスを実現するクラスです。
 *
 * <p>
 * 以下の処理を行います。
 * <ul>
 * <li>全件取得</li>
 * <li>検索</li>
 * <li>追加</li>
 * <li>削除</li>
 * <li>更新</li>
 * </ul>
 * <p>
 * 処理が継続できない場合は、呼び出し元へ例外をスローします。<br>
 * <strong>呼び出し元では適切な例外処理を行ってください。</strong>
 *
 * @author 情報太郎
 */
@Repository
public class UserRepository {

	/** SQL 全件取得（ユーザID昇順） */
	private static final String SQL_SELECT_ALL = "SELECT * FROM user_m order by user_id";
	
	/** SQL 1件取得（ユーザID昇順） */
	private static final String SQL_SELECT_ONE = "SELECT * FROM user_m WHERE user_id =:userId";

	/**SQL　１件追加*/
	private static final String SQL_INSERT_ONE = "INSERT INTO user_m (user_id, encrypted_password, user_name, role, enabled) VALUES(:userId,:encrypted_password ,:user_name,:role, true);";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 全ユーザを取得します。
	 *
	 * <p>
	 * 全レコードを取得するため、 <strong>取得データ量に注意して利用してください。</strong>
	 *
	 * @return 全ユーザレコードの配列
	 */
	public List<Map<String, Object>> selectAll() {
		Map<String, Object> params = new HashMap<>();

		// 埋め込む値が存在しないため、空のMapを生成
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL, params);
		return resultList;
	}
	
	/**
	 * 指定のユーザを取得します。
	 *
	 *
	 * @return 1件のユーザレコードの配列
	 */
	public List<Map<String, Object>> selectOne(String UserId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", UserId);
		// 埋め込む値が存在しないため、空のMapを生成
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ONE, params);
		return resultList;
	}
	
	/**
	 * ユーザ情報を1件登録します
	 * <p>更新件数が異常な場合は例外が発生します
	 * 
	 * @exception IncorrtionResultSizeDataAccessException 更新件数が異常な場合
	 * @param userData ユーザ情報(null不可)
	 * @ruturn 更新件数
	 * */
	public int insert(UserData userData) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userData.getUserId());
		String password = passwordEncoder.encode(userData.getPassword());
		params.put("encrypted_password", password);
		params.put("user_name", userData.getUser_name());
		params.put("role", userData.getRole());
		
		// ユーザ情報の追加処理を実行
		int result = jdbc.update(SQL_INSERT_ONE, params);
		
		return result;
	}
}
