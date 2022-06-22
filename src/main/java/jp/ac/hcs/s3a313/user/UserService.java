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
}
