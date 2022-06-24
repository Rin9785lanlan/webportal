package jp.ac.hcs.s3a313.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity selectAll() {
		List<Map<String, Object>> resultSet;
		try {
			resultSet = userRepository.selectAll();
		} catch (QueryTimeoutException e) {
			// 空のリストを設定
			resultSet = new ArrayList<>();
		}
		UserEntity userEntity = mappingSelectResult(resultSet);
		return userEntity;
	}

	public int insertOne(UserForm form) {
		int resultSet;
		// ユーザ情報の設定
		UserData addData = new UserData();
		addData.setUserId(form.getUserId());
		addData.setUser_name(form.getUsername());
		addData.setPassword(form.getPassword());
		addData.setRole(form.getRole());

		try {
			resultSet = userRepository.insert(addData);
		} catch (QueryTimeoutException e) {
			// 0を設定
			resultSet = 0;

		}
		return resultSet;
	}

	private UserEntity mappingSelectResult(List<Map<String, Object>> resultList) throws DataAccessException {
		UserEntity entity = new UserEntity();

		for (Map<String, Object> map : resultList) {
			UserData data = new UserData();
			data.setUserId((String) map.get("user_id"));
			data.setPassword((String) map.get("encrypted_password"));
			data.setEnabled((boolean) map.get("enabled"));
			data.setUser_name((String) map.get("user_name"));
			data.setRole((String) map.get("role"));

			entity.getUserlist().add(data);
		}
		return entity;
	}

	/**
	 * ユーザ情報を1件取得します
	 *
	 * <p>
	 * UpdateUserFormを返却するため、戻り値を持ちません。
	 *
	 * @param userId         ユーザID(null不可)
	 * @param updateUserForm ユーザ変更の入力値(null不可)
	 */
	public void getUser(String userId, UpdateUserForm updateUserForm) {
		List<Map<String, Object>> resultList;
		try {
			resultList = userRepository.selectOne(userId);
		} catch (DataAccessException e) {
			return;
		}
		mappingUpdateResult(resultList, updateUserForm);
	}

	/**
	 * Userテーブル構造をUpdateUserForm形式に変換します。
	 *
	 * <p>
	 * テーブル構造のためリスト形式で値を取得するが、 1レコードであるため繰り返し処理で上書きしている。
	 *
	 * @param resultList     Userテーブル構造(null可)
	 * @param updateUserForm ユーザ情報(バリデーションエラーを引き継ぐため、Formクラスを返却)
	 */
	private void mappingUpdateResult(List<Map<String, Object>> resultList, UpdateUserForm updateUserForm) {

		for (Map<String, Object> map : resultList) {
			updateUserForm.setUserId((String) map.get("user_id"));
			updateUserForm.setUsername((String) map.get("user_name"));
			updateUserForm.setRole((String) map.get("role"));
			updateUserForm.setEnabled(String.valueOf(map.get("enabled")));
		}
	}
}
