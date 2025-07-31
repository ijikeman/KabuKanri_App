-- 証券会社
INSERT INTO broker_data(name) VALUES
('Monex'),
('SBI証券'),
('SBIネオトレード'),
('みずほ'),
('UFJ'),
('au');

-- 所有者
INSERT INTO owner_data(name) VALUES
('Father'),
('Mother'),
('Brother'),
('Sister');

-- 銘柄種別
INSERT INTO sector_data(name) VALUES
('水産・農林業'),
('食料品'),
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
INSERT INTO security_data(code, name, country_code, sector_id) VALUES
(3923, 'ラクス', 81, 20);
