/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM task_t;

/* タスクテーブルのデータ */
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (1, 'admin', 'このレコードは消さないこと', '2022-11-11', true);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (2, 'user', 'あれやる', '2022-06-24', false);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (3, 'user', 'それやる', '2022-07-11', false);
INSERT INTO task_t (id, user_id, title, limitday, complate) VALUES (4, 'user', 'それやる', '2022-07-11', false);;