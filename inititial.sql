-- 插入100萬筆使用者數據
INSERT INTO users (username, email, created_at)
SELECT
    CONCAT('user_', LPAD(CAST(ROW_NUMBER() OVER () AS TEXT), 6, '0')) AS username,
    CONCAT('user_', LPAD(CAST(ROW_NUMBER() OVER () AS TEXT), 6, '0'), '@example.com') AS email,
    NOW() AS created_at
FROM
    generate_series(1, 1000000);
SELECT COUNT(*) FROM users;

-- 插入10萬筆活動
INSERT INTO activities (activity_name, description, created_at)
SELECT
    CONCAT('activity_', LPAD(CAST(ROW_NUMBER() OVER () AS TEXT), 6, '0')) AS activity_name,
    CONCAT('Description for activity_', LPAD(CAST(ROW_NUMBER() OVER () AS TEXT), 6, '0')) AS description,
    NOW() AS created_at
FROM
    generate_series(1, 100000);
SELECT COUNT(*) FROM activities;

-- 插入30萬個標籤
INSERT INTO activity_tags (tag_name)
SELECT
    CONCAT('tag_', LPAD(CAST(ROW_NUMBER() OVER () AS TEXT), 6, '0')) AS tag_name
FROM
    generate_series(1, 300000);
SELECT COUNT(*) FROM activity_tags;

-- 插入100萬筆活動和標籤對應
INSERT INTO activity_tag_map (activity_id, tag_id)
SELECT
    FLOOR(1 + (RANDOM() * 100000)) AS activity_id,  -- 隨機選擇活動
    FLOOR(1 + (RANDOM() * 300000)) AS tag_id        -- 隨機選擇標籤
FROM
    generate_series(1, 1000000)
ON CONFLICT (activity_id, tag_id) DO NOTHING;

-- 插入50萬筆使用者活動紀錄
INSERT INTO user_activity_logs (user_id, activity_id, participation_date)
SELECT
    u.user_id,  -- 從 users 表中隨機選擇 user_id
    FLOOR(1 + (RANDOM() * 100000))::INT AS activity_id,  -- 隨機選擇活動
    NOW() - INTERVAL '1 day' * FLOOR(RANDOM() * 365)::INT AS participation_date  -- 隨機生成過去一年的參與日期
FROM
    generate_series(1, 500000) gs
    CROSS JOIN LATERAL (SELECT user_id FROM users ORDER BY RANDOM() LIMIT 1) u;
SELECT COUNT(*) FROM user_activity_logs;
