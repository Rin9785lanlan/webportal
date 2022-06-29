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

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS user_m (
    user_id VARCHAR(50) PRIMARY KEY,
    encrypted_password VARCHAR(100),
    user_name VARCHAR(50),
    role VARCHAR(50),
    enabled BOOLEAN
);

/* プロフィールマスタ */
CREATE TABLE IF NOT EXISTS profile_m (
	user_id VARCHAR(50) PRIMARY KEY,
	user_name VARCHAR(50),
	qualification VARCHAR(255),
	nickname VARCHAR(50),
	self_comment VARCHAR(100)
); 