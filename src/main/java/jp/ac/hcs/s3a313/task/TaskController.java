package jp.ac.hcs.s3a313.task;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;

	/**
	 * TODOリスト画面を表示します。
	 *
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return タスク管理画面へのパス(null不可)
	 */
	@PostMapping("/task")
	public String getTask(@AuthenticationPrincipal UserDetails user, Model model) {
		// 結果を取得
		TaskEntity taskEntity = taskService.selectAll(user.getUsername());
		// 結果を画面に設定
		model.addAttribute("taskEntity", taskEntity);

		return "task/list";
	}
	
	/**
	 * 新しい行をリストに追加します
	 *
	 * @param title タスク名(null不可)
	 * @param limit 期限日(null不可)
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * 
	 * @return タスク管理画面へのパス(null不可)
	 */
	@PostMapping("/task/insert")
	public String addTask(@AuthenticationPrincipal UserDetails user, @RequestParam("title") String title, @RequestParam("limit") String limit, Principal principal,
			Model model) {
		// 結果をDBに登録
		TaskEntity taskEntity = taskService.save(user.getUsername(), title, limit);
		// 結果を画面に設定
		model.addAttribute("taskEntity", taskEntity);
		
		return "task/list";
	}
	
	@PostMapping("/task/complate")
	public String updateTask(@AuthenticationPrincipal UserDetails user, @RequestParam("id") String taskId, Model model) {
		//表の更新
		TaskEntity taskEntity = taskService.update(taskId,user.getUsername());
		// 結果を画面に設定
		model.addAttribute("taskEntity", taskEntity);
		
		return "task/list";
	}
	
	@PostMapping("/task/delete")
	public String deleteTask(@AuthenticationPrincipal UserDetails user, @RequestParam("id") String taskId, Model model) {
		//表の更新
		TaskEntity taskEntity = taskService.delete(taskId, user.getUsername());
		// 結果を画面に設定
		model.addAttribute("taskEntity", taskEntity);
		
		return "task/list";
	}
	
	/**
	 * ログイン中のユーザに紐づく、タスクを全件CSVファイルに出力します。
	 * 
	 * <p>本機能は、タスク管理機能のCSV出力機能を提供します。
	 * 
	 * @param principal ログイン中のユーザ情報を格納(null不可)
	 * @param model Viewに値を渡すオブジェクト(null不可)
	 * @return CSVファイル
	 * @throws IOException 
	 */
	@PostMapping("/task/csv")
	public ResponseEntity<byte[]> getTaskCsv(Principal principal, Model model) throws IOException {
		// CSVファイルをサーバ上に作成
		ResponseEntity<byte[]> csv = taskService.taskListCsvOut(principal.getName());
		// CSVファイルを端末へ送信
		return csv;
	}
}
