package jp.ac.hcs.s3a313.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {
	/** SQL 1件取得 */
	private static final String SQL_SELECT_ONE = "SELECT * FROM profile_m WHERE user_id =:userId";

	/** SQL 1件更新 */
	private static final String SQL_UPDATE_ONE = "UPDATE profile_m SET user_name = :name, qualification = :qualification, nickname = :nickname, self_comment = :comment WHERE user_id =:userId";

	/** 予測更新件数(ハードコーティング防止用） */
	private static final int EXPECTED_UPDATE_COUNT = 1;

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	/**
	 * Profileテーブルから取得したデータをProfileEntity形式にマッピングする.
	 * 
	 * @param resultList Userテーブルから取得したデータ
	 * @return UserEntity
	 */
	private ProfileEntity mappingSelectResult(List<Map<String, Object>> resultList) {
		ProfileEntity entity = new ProfileEntity();

		for (Map<String, Object> map : resultList) {
			ProfileData data = new ProfileData();
			data.setUser_id((String) map.get("user_id"));
			data.setUser_name((String) map.get("user_name"));
			data.setQualification((String) map.get("qualification"));
			data.setNickname((String) map.get("nickname"));
			data.setComment((String) map.get("self_comment"));

			entity.getProfileList().add(data);
		}
		return entity;
	}

	/**
	 * ProfileテーブルからユーザIDをキーにデータを1件を取得.
	 * 
	 * @param user_id 検索するユーザID
	 * @return ProfileEntity
	 * @throws DataAccessException
	 */
	public ProfileData selectOne(String user_id) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", user_id);
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ONE, params);
		ProfileEntity entity = mappingSelectResult(resultList);
		// 必ず1件のみのため、最初のUserDataを取り出す
		ProfileData data = entity.getProfileList().get(0);
		return data;
	}

	public int updateOne(ProfileData data) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", data.getUser_id());
		params.put("name", data.getUser_name());
		params.put("qualification", data.getQualification());
		params.put("nickname", data.getNickname());
		params.put("comment", data.getComment());
		int result = jdbc.update(SQL_UPDATE_ONE, params);
		System.out.println(params);

		// if (result != EXPECTED_UPDATE_COUNT) {
		// 更新件数が異常な場合
		// throw new IncorrectResultSizeDataAccessException("更新に失敗しました",
		// EXPECTED_UPDATE_COUNT);
		// }
		return result;
	}
}
