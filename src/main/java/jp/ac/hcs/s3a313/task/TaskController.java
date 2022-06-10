package jp.ac.hcs.s3a313.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String getWeather(Model model) {

		// 結果を取得
		TaskEntity taskEntity = taskService.selectAll("user");
		// 結果を画面に設定
		model.addAttribute("taskEntity", taskEntity);

		return "task/list";
	}
}
