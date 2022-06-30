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
	public int updateOne(ProfileForm form) {
		int resultSet;
		// ユーザ情報の設定
		ProfileData addData = new ProfileData();
		addData.setUser_id(form.getUser_id());
		addData.setUser_name(form.getUser_name());
		addData.setNickname(form.getNickname());
		addData.setQualification(form.getQualification());
		addData.setComment(form.getComment());
		
		try {
			resultSet = ProfileRepository.updata(addData);
		} catch (QueryTimeoutException e) {
			// 0を設定
			resultSet = 0;
		}
		return resultSet;
	}
}
