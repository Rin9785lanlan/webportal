package jp.ac.hcs.s3a313.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
	
	@Autowired
	private SignupRepository signupRepository;

	public int insertOne(SignupForm form) {
		int resultSet;
		// ユーザ情報の設定
		SignupData addData = new SignupData();
		addData.setUserId(form.getUserId());
		addData.setUser_name(form.getUsername());
		addData.setPassword(form.getPassword());
		addData.setRole("ROLE_GENERAL");
		System.out.println(addData);
		try {
			resultSet = signupRepository.insert(addData);
		} catch (QueryTimeoutException e) {
			// 0を設定
			resultSet = 0;
		}
		return resultSet;
	}
}
