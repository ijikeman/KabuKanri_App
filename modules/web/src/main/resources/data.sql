-- 所有者
INSERT INTO owner(name) VALUES
('Father'),
('Mother'),
('Brother'),
('Sister');

-- 証券会社
INSERT INTO broker(name) VALUES
('Monex'),
('SBI証券'),
('SBIネオトレード'),
('みずほ'),
('UFJ'),
('au');

-- 銘柄種別
INSERT INTO sector(name) VALUES
('水産・農林業'), -- 1
('食品'),
('鉱業'),
('石油・石炭製品'),
('建設業'),
('金属製品'),
('土石製品'),
('繊維製品'),
('パルプ・紙'),
('化学'), -- 10
('医薬品'),
('ゴム製品'),
('輸送用機器'),
('鉄鋼'),
('非鉄金属'),
('機械'),
('電気機器'),
('精密機器'),
('その他製品'),
('情報・通信業'), -- 20
('サービス業'),
('電気・ガス業'),
('陸運業');

-- 銘柄
INSERT INTO stock(code, name, country, current_price, latest_dividend, earnings_date, last_updated, sector_id) VALUES
('7203', 'トヨタ自動車', 'jp', NULL, NULL, NULL, NULL, 13),
('6861', 'キーエンス', 'jp', NULL, NULL, NULL, NULL, 17);
