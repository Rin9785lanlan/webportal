/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE task_t;
*/

/* タスクテーブル */
CREATE TABLE IF NOT EXISTS task_t (
  id INT PRIMARY KEY,
  user_id VARCHAR(50),
  title VARCHAR(50),
  limitday DATE,
  complate BOOLEAN
);