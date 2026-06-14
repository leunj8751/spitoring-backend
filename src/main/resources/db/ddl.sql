-- ============================================================
-- 스피또 재고 스냅샷 테이블
-- 크롤링할 때마다 새 행을 INSERT (이력 보관)
-- ============================================================
CREATE TABLE spitto_stock
(
    id              BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT        COMMENT '기본 키',
    spitto_type     VARCHAR(10)      NOT NULL                       COMMENT '스피또 종류 코드 (SP500 / SP1000 / SP2000)',
    game_type_nm    VARCHAR(20)      NOT NULL                       COMMENT '스피또 종류명 (스피또500 / 스피또1000 / 스피또2000)',
    episode         INT UNSIGNED     NOT NULL                       COMMENT '회차 ',
    release_rate    TINYINT UNSIGNED NOT NULL                       COMMENT '판매점 입고율',
    rnk1_remaining  INT UNSIGNED     NOT NULL                       COMMENT '1등 잔여 수량',
    rnk1_total      INT UNSIGNED     NOT NULL                       COMMENT '1등 전체 발행 수량',
    rnk2_remaining  INT UNSIGNED     NOT NULL                       COMMENT '2등 잔여 수량',
    rnk2_total      INT UNSIGNED     NOT NULL                       COMMENT '2등 전체 발행 수량',
    rnk3_remaining  INT UNSIGNED     NOT NULL                       COMMENT '3등 잔여 수량',
    rnk3_total      INT UNSIGNED     NOT NULL                       COMMENT '3등 전체 발행 수량',
    pblcn_qty       INT UNSIGNED     NOT NULL                       COMMENT '발행량',
    inserted_at     DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '크롤링 적재 시간',

    PRIMARY KEY (id),
    INDEX idx_spitto_episode (spitto_type, episode),
    INDEX idx_inserted_at    (inserted_at)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '스피또 재고 스냅샷 (크롤링 이력)';

-- ============================================================
-- 알림 설정 테이블
-- ============================================================
CREATE TABLE notification_setting
(
    id                  BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT        COMMENT '기본 키',
    spitto_type         VARCHAR(10)      NOT NULL                       COMMENT '스피또 종류 코드 (SP500 / SP1000 / SP2000)',
    release_rate        TINYINT UNSIGNED NOT NULL                       COMMENT '알림 기준 입고율 (%)',
    rnk1_remaining_min  INT UNSIGNED     NULL                           COMMENT '1등 잔여 수량 최솟값',
    rnk2_remaining_min  INT UNSIGNED     NULL                           COMMENT '2등 잔여 수량 최솟값',
    user_ip             VARCHAR(45)      NOT NULL                       COMMENT '등록 사용자 IP (IPv6 최대 45자)',
    created_at          DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 날짜',

    PRIMARY KEY (id),
    INDEX idx_spitto_type (spitto_type),
    INDEX idx_user_ip     (user_ip)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '알림 설정';
