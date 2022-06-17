package jp.ac.hcs.s3a313.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	/**
	 * ユーザに対応したタスクを登録する
	 * 
	 * <p>
	 * DBエラーが発生した場合は、エラーメッセージを設定して呼び出し元へ返却します。
	 * 
	 * @param userId ユーザID(null不可)
	 * @return タスク一覧
	 */
	public TaskEntity save(String userId, String title, String limit) {
		// 登録結果
		int result = 0;
		TaskEntity taskEntity = new TaskEntity();
		try {
			// 期限日を日付型に変換
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date limitDate = sdFormat.parse(limit);

			try {
				// 登録データの設定
				TaskData insertData = new TaskData();
				insertData.setUserId(userId);
				insertData.setTitle(title);
				insertData.setLimitday(limitDate);
				// データの登録
				result = taskRepository.save(insertData);

				// リストの生成
				taskEntity = selectAll(userId);

				// メッセージの設定
				if (result == 1) {
					taskEntity.setErrorMessage("登録されました");
				} else {
					taskEntity.setErrorMessage("登録に失敗しました");
				}
				taskEntity.setResult(result);
			} catch (QueryTimeoutException e) {
				// リストの生成
				taskEntity = selectAll(userId);
				taskEntity.setErrorMessage("登録に失敗しました");
				taskEntity.setResult(result);
			}
		} catch (ParseException e) {
			// リストの生成
			taskEntity = selectAll(userId);
			taskEntity.setErrorMessage("登録に失敗しました");
			taskEntity.setResult(result);

		}
		return taskEntity;
	}

	/**
	 * タスクの状態を変更する
	 * 
	 * <p>
	 * DBエラーが発生した場合は、エラーメッセージを設定して呼び出し元へ返却します。
	 * 
	 * @param id     タスクID(null不可)
	 * @param userId ユーザID(null不可)
	 * @return タスク一覧
	 */
	public TaskEntity update(String id, String userId) {
		TaskEntity taskEntity = new TaskEntity();
		int result = 0;
		try {
			// 登録データの設定
			int taskId = Integer.parseInt(id);
			result = taskRepository.update(taskId);
			// リストの生成
			taskEntity = selectAll(userId);
			// 結果の設定
			taskEntity.setErrorMessage("登録しました");
			taskEntity.setResult(result);
		} catch (QueryTimeoutException e) {
			// リストの生成
			taskEntity = selectAll(userId);
			taskEntity.setErrorMessage("更新に失敗しました");
			taskEntity.setResult(result);
		}
		return taskEntity;
	}

	/**
	 * タスクを削除します
	 * 
	 * <p>
	 * DBエラーが発生した場合は、エラーメッセージを設定して呼び出し元へ返却します。
	 * 
	 * @param id     タスクID(null不可)
	 * @param userId ユーザID(null不可)
	 * @return タスク一覧
	 */
	public TaskEntity delete(String id, String userId) {
		TaskEntity taskEntity = new TaskEntity();
		int result = 0;
		try {
			// 登録データの設定
			int taskId = Integer.parseInt(id);
			result = taskRepository.delete(taskId);
			// リストの生成
			taskEntity = selectAll(userId);
			// 結果の設定
			taskEntity.setErrorMessage("削除しました");
			taskEntity.setResult(result);

		} catch (QueryTimeoutException e) {
			// リストの生成
			taskEntity = selectAll(userId);
			taskEntity.setErrorMessage("削除に失敗しました");
			taskEntity.setResult(result);
		}
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
