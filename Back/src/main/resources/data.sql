INSERT INTO user_image (user_image_id, user_image_name, user_image_url, user_origin_image_name) VALUES
                                                                                                    (1, 'user_image1.jpg', 'http://example.com/images/user_image1.jpg', 'user_image_origin1.jpg'),
                                                                                                    (2, 'user_image2.jpg', 'http://example.com/images/user_image2.jpg', 'user_image_origin2.jpg'),
                                                                                                    (3, 'user_image3.jpg', 'http://example.com/images/user_image3.jpg', 'user_image_origin3.jpg'),
                                                                                                    (4, 'user_image4.jpg', 'http://example.com/images/user_image4.jpg', 'user_image_origin4.jpg'),
                                                                                                    (5, 'user_image5.jpg', 'http://example.com/images/user_image5.jpg', 'user_image_origin5.jpg'),
                                                                                                    (6, 'user_image6.jpg', 'http://example.com/images/user_image6.jpg', 'user_image_origin6.jpg'),
                                                                                                    (7, 'user_image7.jpg', 'http://example.com/images/user_image7.jpg', 'user_image_origin7.jpg'),
                                                                                                    (8, 'user_image8.jpg', 'http://example.com/images/user_image8.jpg', 'user_image_origin8.jpg'),
                                                                                                    (9, 'user_image9.jpg', 'http://example.com/images/user_image9.jpg', 'user_image_origin9.jpg'),
                                                                                                    (10, 'user_image10.jpg', 'http://example.com/images/user_image10.jpg', 'user_image_origin10.jpg'),
                                                                                                    (11, 'user_image11.jpg', 'http://example.com/images/user_image11.jpg', 'user_image_origin11.jpg');

INSERT INTO user (user_id, user_image_id, deploy_url, email, password, phone_number, name, gender, birth, rating, registered, registered_date, career, reference_url, introduce, field, join_state)
VALUES
    ( 1,  1, 'https://example.com/u1',  'user1@example.com',  'pw1Secure!', '01012340001', '이지은', false, '1995-03-16', 10,  true, '2024-01-10',  7,  'https://ref.com/u1', '열정적인 프론트엔드 개발자입니다.',            'FRONTEND', true),
    ( 2,  2, 'https://example.com/u2',  'user2@example.com',  'pw2Secure!', '01012340002', '김태리', false, '1996-04-29', 20,  true, '2024-01-11',  3,  'https://ref.com/u2', '백엔드에 관심이 많은 개발자입니다.',            'BACKEND', true),
    ( 3,  3, 'https://example.com/u3',  'user3@example.com',  'pw3Secure!', '01012340003', '박보검',  true, '1997-06-16', 30, true,  '2024-01-17',  2,  'https://ref.com/u3', '풀스택 개발자로 성장하고 싶어요.',           'FULLSTACK', true),
    ( 4,  4, 'https://example.com/u4',  'user4@example.com',  'pw4Secure!', '01012340004', '최민식',  true, '1970-04-27', 40,  true, '2024-01-12', 20,  'https://ref.com/u4', '경험이 풍부한 디자이너입니다.',                 'DESIGN', true),
    ( 5,  5, 'https://example.com/u5',  'user5@example.com',  'pw5Secure!', '01012340005', '정우성',  true, '1973-03-20', 50,  true, '2024-01-13', 25,  'https://ref.com/u5', '사용자 경험을 중시하는 디자이너입니다.',          'DESIGN', true),
    ( 6,  6, 'https://example.com/u6',  'user6@example.com',  'pw6Secure!', '01012340006', '한효주', false, '1987-02-22', 60,  true, '2024-01-14', 10,  'https://ref.com/u6', '데이터베이스를 좋아하는 백엔드 개발자입니다.',    'BACKEND', false),
    ( 7,  7, 'https://example.com/u7',  'user7@example.com',  'pw7Secure!', '01012340007', '송혜교', false, '1982-11-22', 70,  true, '2024-01-15', 15,  'https://ref.com/u7', '최신 웹 기술에 능숙한 프론트엔드 개발자입니다.', 'FRONTEND', true),
    ( 8,  8, 'https://example.com/u8',  'user8@example.com',  'pw8Secure!', '01012340008', '송중기',  true, '1985-09-19', 80,  true, '2024-01-16',  8,  'https://ref.com/u8', '프로젝트 매니지먼트에 능숙합니다.',              'MANAGE', false),
    ( 9,  9, 'https://example.com/u9',  'user9@example.com',  'pw9Secure!', '01012340009', '김수현',  true, '1988-02-16', 90,  false, NULL, 12,  'https://ref.com/u9', '다재다능한 풀스택 개발자입니다.',             'FULLSTACK', false),
    (10, 10, 'https://example.com/u1', 'user10@example.com', 'pw10Secure!', '01012340010', '홍길동',  true, '1990-01-01',  5,  false, NULL,  5, 'https://ref.com/u10', '안녕하세요, 홍길동입니다.',                   'FRONTEND', false),
    (11, 11, 'https://example.com/u2', 'user11@example.com', 'pw11Secure!', '01012340011', '김철수',  true, '1992-02-02',  3, false,    NULL,  3, 'https://ref.com/u11', '반갑습니다, 김철수입니다.',                    'BACKEND', false);

INSERT INTO skill (skill_id, skill_name, skill_image_url, skill_type) VALUES
                                                                         (1, 'Java', 'http://example.com/skills/java.jpg', 'LANGUAGE'),
                                                                         (2, 'React', 'http://example.com/skills/react.jpg', 'FRONTEND'),
                                                                         (3, 'Spring Boot', 'http://example.com/skills/springboot.jpg', 'BACKEND'),
                                                                         (4, 'Selenium', 'http://example.com/skills/selenium.jpg', 'TESTING'),
                                                                         (5, 'PostgreSQL', 'http://example.com/skills/postgresql.jpg', 'DATABASE'),
                                                                         (6, 'TensorFlow', 'http://example.com/skills/tensorflow.jpg', 'DATA'),
                                                                         (7, 'Docker', 'http://example.com/skills/docker.jpg', 'DEVOPS'),
                                                                         (8, 'Git', 'http://example.com/skills/git.jpg', 'TOOL'),
                                                                         (9, 'Photoshop', 'http://example.com/skills/photoshop.jpg', 'DESIGN'),
                                                                         (10, 'Kubernetes', 'http://example.com/skills/kubernetes.jpg', 'DEVOPS');

INSERT INTO user_skill (user_skill_id, user_id, skill_id, skill_level) VALUES
                                                                           (1,  1, 2, 5),  -- 이지은: React (FRONTEND)
                                                                           (2,  1, 8, 4),  -- 이지은: Git (TOOL)
                                                                           (3,  2, 3, 5),  -- 김태리: Spring Boot (BACKEND)
                                                                           (4,  2, 5, 4),  -- 김태리: PostgreSQL (DATABASE)
                                                                           (5,  3, 1, 3),  -- 박보검: Java (LANGUAGE)
                                                                           (6,  3, 2, 6),  -- 박보검: React (FRONTEND)
                                                                           (7,  4, 9, 7),  -- 최민식: Photoshop (DESIGN)
                                                                           (8,  5, 9, 6),  -- 정우성: Photoshop (DESIGN)
                                                                           (9,  6, 5, 5),  -- 한효주: PostgreSQL (DATABASE)
                                                                           (10, 7, 2, 7),  -- 송혜교: React (FRONTEND)
                                                                           (11, 7, 8, 6),  -- 송혜교: Git (TOOL)
                                                                           (12, 8, 7, 5),  -- 송중기: Docker (DEVOPS)
                                                                           (13, 8, 10, 4), -- 송중기: Kubernetes (DEVOPS)
                                                                           (14, 9, 3, 5),  -- 김수현: Spring Boot (BACKEND)
                                                                           (15, 9, 6, 4),  -- 김수현: TensorFlow (DATA)
                                                                           (16, 10, 2, 4), -- 홍길동: React (FRONTEND)
                                                                           (17, 10, 8, 3), -- 홍길동: Git (TOOL)
                                                                           (18, 11, 3, 4), -- 김철수: Spring Boot (BACKEND)
                                                                           (19, 11, 4, 3); -- 김철수: Selenium (TESTING)

-- INSERT INTO schedule (schedule_id, user_id, available_time) VALUES
--                                                                 (1,  1, '08:00:00'),
--                                                                 (2,  1, '14:00:00'),
--                                                                 (3,  2, '09:00:00'),
--                                                                 (4,  2, '15:00:00'),
--                                                                 (5,  3, '10:00:00'),
--                                                                 (6,  3, '16:00:00'),
--                                                                 (7,  4, '11:00:00'),
--                                                                 (8,  4, '17:00:00'),
--                                                                 (9,  5, '12:00:00'),
--                                                                 (10, 5, '18:00:00'),
--                                                                 (11, 6, '13:00:00'),
--                                                                 (12, 6, '19:00:00'),
--                                                                 (13, 7, '07:00:00'),
--                                                                 (14, 7, '20:00:00'),
--                                                                 (15, 8, '08:00:00'),
--                                                                 (16, 8, '21:00:00'),
--                                                                 (17, 9, '09:00:00'),
--                                                                 (18, 9, '22:00:00'),
--                                                                 (19, 10, '10:00:00'),
--                                                                 (20, 10, '23:00:00'),
--                                                                 (21, 11, '11:00:00'),
--                                                                 (22, 11, '12:00:00');
--
-- INSERT INTO reservation (reservation_id, leader_id, member_id, reservation_datetime) VALUES
--                                                                                          (1,  1, 2, '2024-01-24 08:00:00'),
--                                                                                          (2,  1, 3, '2024-01-24 14:00:00'),
--                                                                                          (3,  2, 1, '2024-01-25 09:00:00'),
--                                                                                          (4,  2, 3, '2024-01-25 15:00:00'),
--                                                                                          (5,  3, 4, '2024-01-26 10:00:00'),
--                                                                                          (6,  3, 5, '2024-01-26 16:00:00'),
--                                                                                          (7,  4, 1, '2024-01-27 11:00:00'),
--                                                                                          (8,  4, 2, '2024-01-27 17:00:00'),
--                                                                                          (9,  5, 1, '2024-01-28 12:00:00'),
--                                                                                          (10, 5, 3, '2024-01-28 18:00:00'),
--                                                                                          (11, 6, 7, '2024-01-29 13:00:00'),
--                                                                                          (12, 7, 6, '2024-01-29 07:00:00'),
--                                                                                          (13, 8, 9, '2024-01-30 08:00:00'),
--                                                                                          (14, 9, 8, '2024-01-30 09:00:00'),
--                                                                                          (15, 10, 11, '2024-01-31 10:00:00'),
--                                                                                          (16, 11, 10, '2024-01-31 11:00:00');
-- -- 데이터 정합성에 맞는지 체크 필요

INSERT INTO team (team_id, team_name, team_status, max_team_member, team_desc) VALUES
                                                                                   (1, 'Alpha Team', 'COMPLETE', 4, '다양한 프로젝트를 성공적으로 이끌어가는 팀입니다.'),
                                                                                   (2, 'Beta Team', 'BUILDING', 4, '새로운 아이디어로 뭉친 혁신적인 팀입니다.'),
                                                                                   (3, 'Gamma Team', 'BUILDING', 4, '각 분야의 전문가들이 모여 협업하는 팀입니다.');

INSERT INTO user_team (user_team_id, user_id, team_id, role, member_status) VALUES
                                                                                (1, 1, 1, 'LEADER', 'JOINED'), -- 이지은, 'Alpha Team'의 팀장
                                                                                (2, 2, 1, 'MEMBER', 'JOINED'), -- 김태리, 'Alpha Team'의 팀원
                                                                                (3, 3, 1, 'MEMBER', 'JOINED'), -- 박보검, 'Alpha Team'의 팀원
                                                                                (4, 4, 1, 'MEMBER', 'JOINED'), -- 최민식, 'Alpha Team'의 팀원, 'Alpha Team'은 'COMPLETE' 상태
                                                                                (5, 5, 2, 'LEADER', 'JOINED'), -- 정우성, 'Beta Team'의 팀장
                                                                                (6, 6, 2, 'MEMBER', 'APPLIED'), -- 한효주, 'Beta Team'의 팀원, 'Beta Team'은 'BUILDING' 상태
                                                                                (7, 7, 3, 'LEADER', 'JOINED'), -- 송혜교, 'Gamma Team'의 팀장
                                                                                (8, 8, 3, 'MEMBER', 'SUGGESTED'); -- 송중기, 'Gamma Team'의 팀원, 'Gamma Team'은 'BUILDING' 상태

INSERT INTO team_skill (team_skill_id, team_id, skill_id) VALUES
                                                              (1, 1, 1), -- Alpha Team: Java
                                                              (2, 1, 2), -- Alpha Team: React
                                                              (3, 1, 3), -- Alpha Team: Spring Boot
                                                              (4, 1, 4), -- Alpha Team: Selenium
                                                              (5, 2, 5), -- Beta Team: PostgreSQL
                                                              (6, 2, 6), -- Beta Team: TensorFlow
                                                              (7, 2, 7), -- Beta Team: Docker
                                                              (8, 3, 8), -- Gamma Team: Git
                                                              (9, 3, 9), -- Gamma Team: Photoshop
                                                              (10, 3, 10); -- Gamma Team: Kubernetes
--  'Alpha Team'에는 주로 개발 및 테스팅 관련 기술이, 'Beta Team'에는 데이터베이스 및 데이터 사이언스 관련 기술이, 그리고 'Gamma Team'에는 버전 관리, 디자인, 컨테이너화 기술이 할당되었습니다


INSERT INTO project_image (project_image_id, project_image_name, project_image_url, project_origin_image_name) VALUES
                                                                                                                   (1, 'project_alpha_logo.jpg', 'https://example.com/images/project_alpha_logo.jpg', 'original_alpha_logo.jpg'),
                                                                                                                   (2, 'project_beta_logo.jpg', 'https://example.com/images/project_beta_logo.jpg', 'original_beta_logo.jpg'),
                                                                                                                   (3, 'project_gamma_logo.jpg', 'https://example.com/images/project_gamma_logo.jpg', 'original_gamma_logo.jpg');

INSERT INTO hackathon (hackathon_id, hackathon_name, register_date, team_deadline_date, start_date, end_date, max_point) VALUES
                                                                                                                             (1, 'Winter Code Gala 2023', '2023-01-01', '2023-01-20', '2023-02-10', '2023-02-15', 100),
                                                                                                                             (2, 'Spring Into Code 2023', '2023-03-05', '2023-03-25', '2023-04-15', '2023-04-20', 100),
                                                                                                                             (3, 'Summer Hack 2023', '2023-05-10', '2023-06-01', '2023-07-05', '2023-07-10', 100),
                                                                                                                             (4, 'Spring Hackathon 2024', '2024-03-01', '2024-04-01', '2024-05-15', '2024-05-20', 100),
                                                                                                                             (5, 'Summer Code Fest 2024', '2024-05-01', '2024-06-01', '2024-07-20', '2024-07-25', 100),
                                                                                                                             (6, 'Fall Developers Conference 2024', '2024-08-01', '2024-09-01', '2024-10-05', '2024-10-10', 100);

INSERT INTO hackathon_image (hackathon_image_id, hackathon_id, hackathon_image_name, hackathon_image_url, hackathon_origin_image_name) VALUES
                                                                                                                                           (1, 1, 'winter_code_gala_2023.jpg', 'https://example.com/images/winter_code_gala_2023.jpg', 'original_winter_gala.jpg'),
                                                                                                                                           (2, 2, 'spring_code_2023.jpg', 'https://example.com/images/spring_code_2023.jpg', 'original_spring_code.jpg'),
                                                                                                                                           (3, 3, 'summer_hack_2023.jpg', 'https://example.com/images/summer_hack_2023.jpg', 'original_summer_hack.jpg'),
                                                                                                                                           (4, 4, 'spring_hackathon_2024.jpg', 'https://example.com/images/spring_hackathon_2024.jpg', 'original_spring_hackathon.jpg'),
                                                                                                                                           (5, 5, 'summer_code_fest_2024.jpg', 'https://example.com/images/summer_code_fest_2024.jpg', 'original_summer_fest.jpg'),
                                                                                                                                           (6, 6, 'fall_dev_conference_2024.jpg', 'https://example.com/images/fall_dev_conference_2024.jpg', 'original_fall_conference.jpg');

-- INSERT INTO project (project_id, team_id, hackathon_id, project_image_id, project_name, project_topic, project_desc, project_status, registered_date, start_date, end_date, project_url, hackathon_score, win_state, deploy_url) VALUES
--                                                                                                                                                                                                                                      (1, 1, 4, 4, 'Alpha Innovation', 'AI', '인공지능을 활용한 혁신 프로젝트', 'REGISTERED', '2024-02-01', '2024-05-15', '2024-05-20', NULL, 0, false, NULL),
--                                                                                                                                                                                                                                      (2, 2, 5, 5, 'Beta Revolution', 'Blockchain', '블록체인 기반 투명 거래 시스템', 'REGISTERED', '2024-04-01', '2024-07-20', '2024-07-25', NULL, 0, false, NULL),
--                                                                                                                                                                                                                                      (3, 3, 6, 6, 'Gamma Challenge', 'Big Data', '대용량 데이터 처리와 분석', 'REGISTERED', '2024-08-15', '2024-10-05', '2024-10-10', NULL, 0, false, NULL);



-- CREATE TABLE post
-- (
--     `post_id`      bigint      NOT NULL COMMENT 'auto_increment',
--     `user_id`      bigint      NOT NULL,
--     `project_id`   bigint      NOT NULL,
--     `post_title`   varchar(40) NOT NULL,
--     `post_content` TEXT        NOT NULL
-- );
--
-- CREATE TABLE post_file_id
-- (
--     `post_file_id`     bigint       NOT NULL COMMENT 'auto_increment',
--     `post_id`          int          NOT NULL,
--     `file_name`        varchar(50)  NOT NULL,
--     `file_url`         varchar(100) NOT NULL,
--     `origin_file_name` varchar(50) NULL
-- );
--
-- CREATE TABLE back_performance
-- (
--     `back_performance_id` int         NOT NULL,
--     `project_id`          bigint      NOT NULL,
--     `file_name`           varchar(300) NULL,
--     `severity`            varchar(20) NOT NULL,
--     `line`                int NULL,
--     `message`             TEXT NULL,
--     `type`                varchar(20) NOT NULL
-- );
--
-- CREATE TABLE project_contribution
-- (
--     `project_contribution_id` VARCHAR(255) NOT NULL,
--     `project_id`              bigint       NOT NULL,
--     `author`                  varchar      NOT NULL,
--     `row`                     int          NOT NULL,
--     `ratio_in_comments` double NOT NULL,
--     `commits`                 int          NOT NULL,
--     `insertions`              int          NOT NULL,
--     `deletions`               int          NOT NULL,
--     `ratio_in_changes`        float        NOT NULL
-- );
--
-- CREATE TABLE front_performance
-- (
--     `front_performance_id` int    NOT NULL,
--     `project_id`          bigint NOT NULL,
--     `performance`         int    NOT NULL COMMENT '얼마나 빨리 컨텐츠를 표시하는지',
--     `accessibility`       int    NOT NULL COMMENT '얼마나 쉽게 접근할 수 있는지',
--     `best_practices`      int    NOT NULL COMMENT '웹 규정을 얼마나 잘 준수하는지',
--     `seo`                 int    NOT NULL COMMENT '검색 엔진 기능 최적화',
--     `pwa`                 int    NOT NULL COMMENT '모바일에서도 잘돌아가는지'
-- );
--
-- CREATE TABLE project_like
-- (
--     `project_like_id`    bigint NOT NULL COMMENT 'auto_increment',
--     `user_id`    bigint NOT NULL,
--     `project_id` bigint NOT NULL
-- );