package jp.ac.hcs.s3a313.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * タスク管理にかかわるDBアクセスを実現するクラスです
 * 
 * <p>以下の処理を行います
 * <ul>
 * <li>検索</li>
 * <li>追加</li>
 * <li>削除</li>
 * <li>更新</li>
 * </ul>
 * <p>処理が継続できない場合は、呼び出し元へ例外ををスローします
 * <strong>呼び出し元では適切な例外処理を行ってください</strong>
 * 
 * @author s20203053
 * */

@Repository
public class TaskRepository {
	/**SQL　全件取得（期限日昇順*/
	private static final String SQL_SELECT_ALL = "SELECT * FROM task_t WHERE user_id =:userId order by limitday";
	
	/**SQL　１件追加*/
	private static final String SQL_INSERT_ONE = "INSERT INTO task_t(id,user_id,title,limitday,complate) VALUES ((SELECT MAX(id) + 1 FROM task_t),:userId, :title, :limitday, false)";
	
	/**SQL　１件削除*/
	private static final String SQL_DELETE_ONE = "DELETE FROM task_t WHERE id = :id";
	
	/**SQL　１件更新*/
	private static final String SQL_UPDATE_ONE = "UPDATE task_t SET complate = true WHERE id = :id";
	
	/**予測更新件数(ハードコーティング防止用）*/
	private static final int EXPECTD_UPDATE_COUNT = 1;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	/**
	 * 指定したユーザIDに合致するタスク情報を全権取得します
	 * 
	 * <p>合致するタスクがない場合は、からの配列が返却されます。
	 * 
	 * @param userID ユーザID（null不可)
	 * @return 検索結果配列
	 * */
	
	public List<Map<String,Object>> findAll(String userId){
		Map<String,Object> params = new HashMap<String,Object>();
		//カラム名とパラメ－タ
		params.put("userId",userId);
		
		List<Map<String,Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL,params);
		return resultList;
	}
	
	/**
	 * タスクを１件保存します
	 * 
	 * <p>更新件数が異常な場合は例外が発生します
	 * 
	 * @exception IncorrectResultSizeDataAccessException 更新件数が異常な場合
	 * @param taskData タスク(null不可)
	 * @return 更新件数
	 * */
	
	public int save(TaskData taskData){
		Map<String,Object> params = new HashMap<String,Object>();
		//カラム名とパラメ－タ
		params.put("userId",taskData.getUserId());
		params.put("title",taskData.getTitle());
		params.put("limitday",taskData.getLimitday());
		int updateRow = jdbc.update(SQL_INSERT_ONE, params);
		if (updateRow != EXPECTD_UPDATE_COUNT) {
			//更新件数が異常な場合
			throw new IncorrectResultSizeDataAccessException("更新に失敗しました",EXPECTD_UPDATE_COUNT);
		}
		return updateRow;
	}
		



/**
 * タスクを１件削除します
 * 
 * <p>削除件数が異常な場合は例外が発生します
 * 
 * @exception IncorrectResultSizeDataAccessException 削除件数が異常な場合
 * @param id タスクid(null不可)
 * @return 削除件数
 * */

public int delete(int id){
	Map<String,Object> params = new HashMap<String,Object>();
	//カラム名とパラメ－タ
	params.put("id",id);
	
	int updateRow = jdbc.update(SQL_DELETE_ONE, params);
	if (updateRow != EXPECTD_UPDATE_COUNT) {
		//更新件数が異常な場合
		throw new IncorrectResultSizeDataAccessException("更新に失敗しました",EXPECTD_UPDATE_COUNT);
	}
	return updateRow;
	}
	
/**
 * タスクを１件「完了」状態にします
 * 
 * @exception IncorrectResultSizeDataAccessException 削除件数が異常な場合
 * @param id タスクid(0不可)
 * @return 更新件数
 * */

public int update(int id){
	Map<String,Object> params = new HashMap<String,Object>();
	//カラム名とパラメ－タ
	params.put("id",id);
	
	int updateRow = jdbc.update(SQL_UPDATE_ONE, params);
	if (updateRow != EXPECTD_UPDATE_COUNT) {
		//更新件数が異常な場合
		throw new IncorrectResultSizeDataAccessException("更新に失敗しました",EXPECTD_UPDATE_COUNT);
	}
	return updateRow;
	}
}
