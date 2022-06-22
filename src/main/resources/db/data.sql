/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM task_t;
DELETE FROM user_m;

/* タスクテーブルのデータ */
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (1, 'admin', 'このレコードは消さないこと', '2022-11-11', true);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (2, 'user', 'あれやる', '2022-06-24', false);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (3, 'user', 'それやる', '2022-07-11', false);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (4, 'user', 'それやる', '2022-07-11', false);;

/* ユーザマスタのデータ（ADMIN権限） PASS:hcs2022 */
INSERT INTO user_m (user_id, encrypted_password, user_name, role, enabled)
VALUES('taro@xxx.co.jp', '$2a$10$yLURcbhXoVslVn5I0yQr3eBu088EP1XRfyYXpjGCEt993zbVmbjES', '情報太郎', 'ROLE_ADMIN', true);
/* ユーザマスタのデータ（上位権限） PASS:hcs2022 */
INSERT INTO user_m (user_id, encrypted_password, user_name, role, enabled)
VALUES('hanako@xxx.co.jp', '$2a$10$yLURcbhXoVslVn5I0yQr3eBu088EP1XRfyYXpjGCEt993zbVmbjES', '情報花子', 'ROLE_TOP', true);
/* ユーザマスタのデータ（一般権限） PASS:hcs2022 */
INSERT INTO user_m (user_id, encrypted_password, user_name, role, enabled)
VALUES('goro@xxx.co.jp', '$2a$10$yLURcbhXoVslVn5I0yQr3eBu088EP1XRfyYXpjGCEt993zbVmbjES', '情報五郎', 'ROLE_GENERAL', true);