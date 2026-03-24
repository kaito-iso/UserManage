DELETE FROM m_users;
DELETE FROM m_departments;

INSERT INTO m_users (
	user_id
, password
,	role
,	family_name
,	given_name
,	family_name_kana
,	given_name_kana
,	mail
,	account_enabled
,	add_date
,	upd_date
,	last_login_date
)
VALUES (
	'kaito'
,	'$2a$10$j.TrzVKf6neZ2Reul4arBec8RWGtaxVUurKp0L1umh40iW9vjVZLa'
,	'ROLE_ADMIN'
,	'佐藤'
,	'太郎'
,	'サトウ'
,	'タロウ'
,	'abc@abc'
,	true
,	'2025-12-24 22:14:07.382481'
,	'2025-12-24 22:14:07.382481'
,	'2025-12-24 22:14:07.382481'
);

INSERT INTO m_users (
	user_id
, password
,	role
,	family_name
,	given_name
,	family_name_kana
,	given_name_kana
,	mail
,	account_enabled
,	add_date
,	upd_date
)
VALUES (
	'user'
,	'$2a$10$uQOAxGqDZ05J56SrBzyld.S2mWNBFJHmgesf.JRIAPktylbn2WgB.'
,	'ROLE_USER'
,	'user'
,	'user'
,	''
,	''
,	'abc@abc111'
,	true
,	'2025-12-24 22:14:07.382481'
,	'2025-12-24 22:14:07.382481'
);

-- 部署マスタ
INSERT INTO m_departments VALUES('SM','総務部',10);
INSERT INTO m_departments VALUES('KR','経理部',20);
INSERT INTO m_departments VALUES('ZH','情報部',30);
INSERT INTO m_departments VALUES('EG1','営業1部',40);
INSERT INTO m_departments VALUES('EG2','営業2部',50);

-- 役職マスタ
INSERT INTO m_position VALUES ('000', '会長', 1);
INSERT INTO m_position VALUES ('005', '社長', 2);
INSERT INTO m_position VALUES ('010', '副社長', 3);
INSERT INTO m_position VALUES ('015', '専務取締役', 4);
INSERT INTO m_position VALUES ('020', '専務取締役', 5);
INSERT INTO m_position VALUES ('025', '本部長', 6);
INSERT INTO m_position VALUES ('030', '部長', 7);
INSERT INTO m_position VALUES ('035', '次長', 8);
INSERT INTO m_position VALUES ('040', '課長', 9);
INSERT INTO m_position VALUES ('045', '係長', 10);
INSERT INTO m_position VALUES ('050', '主任', 11);
INSERT INTO m_position VALUES ('100', '一般', 12);
INSERT INTO m_position VALUES ('999', 'その他', 99);

--　お知らせカテゴリーマスタ
INSERT INTO m_announcements_category VALUES ('AA', '重要', 1, 'bg-danger');
INSERT INTO m_announcements_category VALUES ('BB', 'お知らせ', 2, 'bg-primary');
INSERT INTO m_announcements_category VALUES ('CC', 'メンテ', 3, 'bg-secondary');

-- お知らせ
INSERT INTO t_announcements
VALUES ( 100,'タイトルAAA','内容111','AA','2026-01-01 22:00:00','2026-12-31 22:00:00','kaito','2026-02-01 22:00:00','2026-02-01 22:00:00');
INSERT INTO t_announcements
VALUES ( 101,'タイトルBBB','内容222','BB','2026-01-03 22:00:00','2026-12-31 22:00:00','kaito','2026-02-01 22:00:00','2026-02-01 22:00:00');
INSERT INTO t_announcements
VALUES ( 102,'タイトルCCC','内容333','CC','2026-01-03 22:00:00','2026-12-31 22:00:00','kaito','2026-02-01 22:00:00','2026-02-01 22:00:00');

INSERT INTO m_users_departments VALUES ('ZH', 'kaito', TRUE);
INSERT INTO m_users_departments VALUES ('KR', 'kaito', FALSE);
INSERT INTO m_users_departments VALUES ('SM', 'kaito', FALSE);