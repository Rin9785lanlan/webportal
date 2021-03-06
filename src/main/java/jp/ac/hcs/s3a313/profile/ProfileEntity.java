package jp.ac.hcs.s3a313.profile;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProfileEntity {

	/**
	 * プロフィール情報を取得するリスト
	 */
	private List<ProfileData> profileList = new ArrayList<ProfileData>();

	/**
	 * 取得資格を管理するリスト
	 */
	private List<ProfileData> qualification = new ArrayList<ProfileData>();
}
