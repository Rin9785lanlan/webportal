package jp.ac.hcs.s3a313.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import jp.ac.hcs.s3a313.WebConfig;

/**
 * 
 * タスク管理機能のCSV出力機能を実現します。
 * 
 * <p>ファイル出力先を事前に設定することで、所定のパスへCSVを出力します。
 * 
 * @author 情報太郎
 *
 */
public class TaskRowCallbackHandler implements RowCallbackHandler {

	@Override
	public void processRow(ResultSet rs) throws SQLException {

		// フォルダへアクセス
		File directory = new File(WebConfig.OUTPUT_PATH);
		if (!directory.exists()) {
			// フォルダが存在しない場合は作成する
			directory.mkdir();
		}

		// ファイルを作成
		File file = new File(WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV);

		// ファイルへ書き込む
		try (FileWriter fw = new FileWriter(file.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw)) {

			do {
				// カンマ区切りでデータを並べた文字列を生成
				String str = rs.getInt("id") + ","
						+ rs.getString("user_id") + ","
						// FIXME カンマを入力されると不具合が発生する
						+ rs.getString("title") + ","
						+ rs.getDate("limitday");

				bw.write(str);
				bw.newLine();
			} while (rs.next());

		} catch (IOException e) {
			// ファイル書き込み時で発生したエラー
			throw new SQLException(e);
		}
	}
}