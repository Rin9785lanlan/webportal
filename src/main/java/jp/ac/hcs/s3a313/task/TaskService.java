package jp.ac.hcs.s3a313.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク機能の業務ロジックを処理します。
 * 
 * <p>
 * 本クラスは以下の処理を主に行います。
 * <ul>
 * <li>データの相互変換</li>
 * <li>例外処理</li>
 * <li>Repositoryクラスへの問い合わせ</li>
 * <li>Controllerクラスへのリターン</li>
 * </ul>
 * 
 * @author 情報太郎
 */
@Transactional
@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	/**
	 * ユーザIDに合致するタスク一覧を取得します。
	 * 
	 * <p>
	 * DBエラーが発生した場合は、空のタスク一覧を設定して呼び出し元へ返却します。
	 * 
	 * @param userId ユーザID(null不可)
	 * @return タスク一覧
	 */
	public TaskEntity selectAll(String userId) {
		List<Map<String, Object>> resultSet;
		try {
			resultSet = taskRepository.findAll(userId);
		} catch (QueryTimeoutException e) {
			// 空のリストを設定
			resultSet = new ArrayList<>();
		}
		TaskEntity taskEntity = mappingSelectResult(resultSet);
		return taskEntity;
	}

	private TaskEntity mappingSelectResult(List<Map<String, Object>> resultList) throws DataAccessException {
		TaskEntity entity = new TaskEntity();

		for (Map<String, Object> map : resultList) {
			TaskData data = new TaskData();
			data.setId((Integer) map.get("id"));
			data.setUserId((String) map.get("user_id"));
			data.setTitle((String) map.get("title"));
			data.setLimitday((Date) map.get("limitday"));
			data.setComplate((boolean) map.get("complate"));

			entity.getTaskList().add(data);
		}
		return entity;
	}
}