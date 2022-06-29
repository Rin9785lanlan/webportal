package jp.ac.hcs.s3a313.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	/**
	 * プロフィール情報を1件取得します
	 *
	 * @param String ユーザ名(null不可)
	 * 
	 * @return プロフィールデータ(null不可)
	 */
	public ProfileData selectAll(String userId) {
		ProfileData profileData = new ProfileData(); 
		try {
			profileData = profileRepository.selectOne(userId);
		}catch(QueryTimeoutException e) {
			profileData = null;
		}
		return profileData;
	}
}
