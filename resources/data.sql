--roleテーブル
INSERT IGNORE INTO role (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO role (id, name) VALUES (2, 'ROLE_ADMIN');



--userテーブル
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (1, '侍太郎', 'サムライタロウ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '01', '02', '男性', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true ); 
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (2, '侍花子', 'サムライハナコ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '03', '02', '女性', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (3, '侍 義勝', 'サムライ ヨシカツ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '10', '02', '男性',  'yoshikatsu.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (4, '侍 幸美', 'サムライ サチミ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '12', '2', '女性', 'sachimi.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (5, '侍 雅', 'サムライ ミヤビ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '11', '23', '女性', 'miyabi.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (6, '侍 正保', 'サムライ マサヤス', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '10', '02', '男性', 'masayasu.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (7, '侍 真由美', 'サムライ マユミ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '11', '23', '女性', 'mayumi.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (8, '侍 安民', 'サムライ ヤスタミ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '09', '02', '男性', 'yasutami.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (9, '侍 章緒', 'サムライ アキオ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '04', '12', '男性', 'akio.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (10, '侍 祐子', 'サムライ ユウコ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '08', '19', '女性', 'yuko.samurai@example.com', 'password', 1, false );
INSERT IGNORE INTO user (id, name, furigana, postal_code, address, phone_number, birthmonth, birthday, gender, email, password, role_id, enabled) VALUES (11, '侍 秋美', 'サムライ アキミ', '111-1111', '東京都千代田区神田練塀町300番地', '090-1234-5678', '08', '19', '女性', 'akimi.samurai@example.com', 'password', 1, false );


--categoryテーブル
--shopテーブル
--favoriteテーブル
--reviewテーブル
--reservationテーブル




